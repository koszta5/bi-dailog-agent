package cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache;

public interface CachePostProcessor<AbstractCache> {

	public void processCache(cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache.AbstractCache<?> abstractCache);

	
}
