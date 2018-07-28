package cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.InitializingBean;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.collect.ImmutableMap;


public abstract class AbstractCache<T>  implements Cache<String, T>, InitializingBean {
	
	public void refresh(){
		load();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		putAll(load());
	}

	private Cache<String, T> embeddedCache;

	public AbstractCache() {
		super();
		embeddedCache = CacheBuilder.newBuilder().build();
	}

	@Override
	public void cleanUp() {
		embeddedCache.cleanUp();
	
	}

	public Stream<T> convertIteratorToStream(Iterator<T> sourceIterator){
		Iterable<T> iterable = () -> sourceIterator;
		return StreamSupport.stream(iterable.spliterator(), false);
	}

	@Override
	public ImmutableMap<String, T> getAllPresent(Iterable<?> arg0) {
		return embeddedCache.getAllPresent(arg0);
	}

	@Override
	public T getIfPresent(Object arg0) {
		return embeddedCache.getIfPresent(arg0);
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
	public void put(String arg0, T arg1) {
		embeddedCache.put(arg0, arg1);
	
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
	public ConcurrentMap<String, T> asMap() {
		return embeddedCache.asMap();
	}

	public abstract Map<String, T> load();

	@Override
	public T get(String key, Callable<? extends T> loader) throws ExecutionException {
		return embeddedCache.get(key, loader);
	}

	@Override
	public void putAll(Map<? extends String, ? extends T> m) {
		embeddedCache.putAll(m);
		
	}

}