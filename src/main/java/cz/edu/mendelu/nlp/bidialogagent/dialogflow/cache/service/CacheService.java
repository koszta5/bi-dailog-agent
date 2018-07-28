package cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache.AbstractCache;
import cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache.CachePostProcessor;
import cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache.EntityTypeCache;

@Service
public class CacheService {
	
	private final Logger log = LoggerFactory.getLogger(EntityTypeCache.class);


	private List<AbstractCache<?>> allCaches;
	private List<CachePostProcessor<?>> cachePostProcessors;

	@Autowired
	public void setAllCaches(List<AbstractCache<?>> allCaches) {
		this.allCaches = allCaches;
	}
	
	
	
	public void setCachePostProcessors(List<CachePostProcessor<?>> cachePostProcessors) {
		this.cachePostProcessors = cachePostProcessors;
	}



	private Optional<AbstractCache<?>> getByName(String cacheClassSimpleName){
		return 
				allCaches.stream()
				.filter(cache -> cache.getClass().getSimpleName().equals(cacheClassSimpleName))
				.findFirst();
	}
	
	public List<String> listCaches (){
		return allCaches.stream()
				.map(AbstractCache::getClass)
				.map(Class::getSimpleName)
				.collect(Collectors.toList());
	}
	public Map<String, ?> listCache(String cacheName) {
		return (Map<String, ?>) 
				this.getByName(cacheName)
				.get().asMap()
				.entrySet()
				.stream()
				.collect(Collectors.toMap(Entry::getKey, e -> e.getValue().toString()))
				;
	}
	
	public Boolean refresh (String cacheName){
		try {
			this.getByName(cacheName)
			.get().invalidateAll();
			this.getByName(cacheName)
			.get().load();
			List<CachePostProcessor<?>> postProcessors = getPostProcessors(cacheName);
			postProcessors
				.stream()
				.forEach(pp -> pp.processCache(this.getByName(cacheName).get()));
			return true;
		}
		catch (Exception e){
			return false;
		}
	}


	public boolean isPostProcessorFor(String className, CachePostProcessor<?> postProcessor){
		try {
			Class<?> searchFor = Class.forName(className);
			Class<?> postProcessorParametricClass = ((Class) ((ParameterizedType) postProcessor.getClass()
			        .getGenericSuperclass()).getActualTypeArguments()[0]);
			return searchFor.equals(postProcessorParametricClass.getSimpleName());
			
		} catch (ClassNotFoundException e) {
			log.error("Could not find post processors for "+className);
			throw new RuntimeException(e);
		}
		
	}

	private List<CachePostProcessor<?>> getPostProcessors(String className) {
		return cachePostProcessors.stream()
			.filter(pp -> this.isPostProcessorFor(className, pp))
			.collect(Collectors.toList());
		
	}
}
