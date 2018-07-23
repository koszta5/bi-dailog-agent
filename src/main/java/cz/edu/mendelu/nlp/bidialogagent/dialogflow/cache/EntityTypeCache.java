package cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.cloud.dialogflow.v2.EntityType;
import com.google.cloud.dialogflow.v2.EntityTypesClient;
import com.google.cloud.dialogflow.v2.ProjectAgentName;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;

import cz.edu.mendelu.nlp.bidialogagent.GCloudConfiguration;

@Component
public class EntityTypeCache implements LoadingCache<String, EntityType>, InitializingBean {

	@Autowired
	private GCloudConfiguration config;

	private LoadingCache<String, EntityType> embeddedCache;
	private boolean loaded = false;

	@Override
	public void cleanUp() {
		embeddedCache.cleanUp();

	}

	@Override
	public EntityType get(String arg0, Callable<? extends EntityType> arg1) throws ExecutionException {
		return embeddedCache.get(arg0, arg1);
	}

	@Override
	public ImmutableMap<String, EntityType> getAllPresent(Iterable<?> arg0) {
		return embeddedCache.getAllPresent(arg0);
	}

	@Override
	public EntityType getIfPresent(Object arg0) {
		return getIfPresent(arg0);
	}

	@Override
	public void invalidate(Object arg0) {
		embeddedCache.invalidate(arg0);

	}

	@Override
	public void invalidateAll() {
		embeddedCache.invalidateAll();

	}

	@Override
	public void invalidateAll(Iterable<?> arg0) {
		embeddedCache.invalidateAll(arg0);
	}

	@Override
	public void put(String arg0, EntityType arg1) {
		embeddedCache.put(arg0, arg1);

	}

	@Override
	public void putAll(Map<? extends String, ? extends EntityType> arg0) {
		embeddedCache.putAll(arg0);

	}

	@Override
	public long size() {
		return embeddedCache.size();
	}

	@Override
	public CacheStats stats() {
		return embeddedCache.stats();
	}

	@Override
	public EntityType apply(String arg0) {
		return embeddedCache.apply(arg0);
	}

	@Override
	public ConcurrentMap<String, EntityType> asMap() {
		return embeddedCache.asMap();
	}

	@Override
	public EntityType get(String arg0) throws ExecutionException {
		return embeddedCache.get(arg0);
	}

	@Override
	public ImmutableMap<String, EntityType> getAll(Iterable<? extends String> arg0) throws ExecutionException {
		return embeddedCache.getAll(arg0);
	}

	@Override
	public EntityType getUnchecked(String arg0) {
		return embeddedCache.getUnchecked(arg0);
	}

	@Override
	public void refresh(String arg0) {
		embeddedCache.refresh(arg0);

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		final EntityTypeCache selfRef = this;
		loadEntityTypes();
		embeddedCache = CacheBuilder.newBuilder().build(new CacheLoader<String, EntityType>() {

			@Override
			public EntityType load(String arg0) throws Exception {
				return selfRef.get(arg0);

			}
		});

	}

	protected void loadEntityTypes() throws IOException {
		try (EntityTypesClient entityTypesClient = EntityTypesClient.create()) {
			// Set the project agent name using the projectID (my-project-id)
			ProjectAgentName parent = ProjectAgentName.of(config.getProjectId());

			// Performs the list entity types request
			for (EntityType entityType : entityTypesClient.listEntityTypes(parent).iterateAll()) {
				this.put(entityType.getDisplayName(), entityType);
			}
		}
	}

}
