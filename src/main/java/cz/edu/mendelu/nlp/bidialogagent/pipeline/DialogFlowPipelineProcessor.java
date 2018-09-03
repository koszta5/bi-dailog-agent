package cz.edu.mendelu.nlp.bidialogagent.pipeline;

import java.util.Optional;

public class DialogFlowPipelineProcessor extends  PipeLineProcessorBase implements PipeLineProcessor {

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
		return true;
	}

	@Override
	public void process() {

	}

}
