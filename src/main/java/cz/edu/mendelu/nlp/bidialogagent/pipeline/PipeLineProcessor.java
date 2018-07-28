package cz.edu.mendelu.nlp.bidialogagent.pipeline;

import java.util.Optional;

import cz.edu.mendelu.nlp.bidialogagent.model.MetaModelRepresntation;
import cz.edu.mendelu.nlp.dialogflow.WebHookRequestJson;

public interface PipeLineProcessor extends Runnable {
	public default run(){
		pro
	}
	public Optional<PipeLineProcessor[]> dependsOn();
	public boolean isDone();
	public boolean canStart();
	public void process(WebHookRequestJson request, MetaModelRepresntation representation);
}
