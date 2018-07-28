package cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.google.cloud.dialogflow.v2.EntityType;
import com.google.cloud.dialogflow.v2.EntityType.Entity;



@Component
@DependsOn(value="entityTypeCache")
public class QueryTypeCache extends AbstractCache<Entity> implements CachePostProcessor<EntityTypeCache> {
	
	private static final String QUERY_TYPE_KEY = "queryType";
	@Autowired
	private EntityTypeCache entityCache;

	

	@Override
	public void refresh() {
		entityCache.load();
		load();
	}

	@Override
	public Map<String, Entity> load() {
		EntityType queryType = entityCache.getIfPresent(QUERY_TYPE_KEY);
		return queryType.getEntitiesList()
			.stream()
			.collect(Collectors.toMap(Entity::getValue, Function.identity()));
		
	}

	@Override
	public void processCache(AbstractCache<?> abstractCache) {
		load();
		
	}

	
	
}
