package cz.edu.mendelu.nlp.bidialogagent.pipeline;

import cz.edu.mendelu.nlp.bidialogagent.model.MetaModelRepresentation;
import cz.edu.mendelu.nlp.dialogflow.WebHookRequestJson;

public abstract class PipeLineProcessorBase implements PipeLineProcessor  {

	protected MetaModelRepresentation metaModelRepresntation;
	protected WebHookRequestJson requestJson;

	@Override
	public void configure(WebHookRequestJson requestJson, MetaModelRepresentation representation) {
		this.metaModelRepresntation = representation;
		this.requestJson = requestJson;
	}
}
