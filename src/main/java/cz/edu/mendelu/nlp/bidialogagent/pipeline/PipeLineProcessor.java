package cz.edu.mendelu.nlp.bidialogagent.pipeline;

import cz.edu.mendelu.nlp.bidialogagent.model.MetaModelRepresentation;
import cz.edu.mendelu.nlp.dialogflow.WebHookRequestJson;

import java.util.Optional;

public interface PipeLineProcessor extends Runnable {
	public default void run() {
		process();
	}

	public Optional<PipeLineProcessor[]> dependsOn();

	public boolean isDone();

	public boolean canStart();

	public void process();

	public void configure(WebHookRequestJson requestJson, MetaModelRepresentation represntation);

}
