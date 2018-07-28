package cz.edu.mendelu.nlp.bidialogagent.pipeline;

import java.util.Optional;

import cz.edu.mendelu.nlp.bidialogagent.model.MetaModelRepresntation;
import cz.edu.mendelu.nlp.dialogflow.WebHookRequestJson;

public class DialogFlowPipelineProcessor implements PipeLineProcessor {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<PipeLineProcessor[]> dependsOn() {
		return Optional.empty();
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canStart() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void process(WebHookRequestJson request, MetaModelRepresntation representation) {
		// TODO Auto-generated method stub
		
	}

}
