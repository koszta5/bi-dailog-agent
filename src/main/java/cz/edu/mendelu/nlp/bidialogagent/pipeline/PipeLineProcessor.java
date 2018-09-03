package cz.edu.mendelu.nlp.bidialogagent.pipeline;

<<<<<<< Updated upstream
import cz.edu.mendelu.nlp.bidialogagent.model.MetaModelRepresentation;
=======
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;

import cz.edu.mendelu.nlp.bidialogagent.model.MetaModelRepresntation;
>>>>>>> Stashed changes
import cz.edu.mendelu.nlp.dialogflow.WebHookRequestJson;

import java.util.Optional;

public interface PipeLineProcessor extends Runnable {
<<<<<<< Updated upstream
	public default void run() {
		process();
=======
	public default run(){
		if (!dependsOn().isPresent()){
			process();
		}
		else {
			while (true){
				if (Arrays.asList(dependsOn().get()).size() > 0){
					List<Object> processors = Arrays.asList(dependsOn().get());
				
					
						.stream()
						.filter(PipeLineProcessor::isDone)
						.findAny().isPresent();
				}
			}
			
		}
	}
	public boolean isDone(PipeLineProcessor p){
		return p.isDone();
>>>>>>> Stashed changes
	}

	public Optional<PipeLineProcessor[]> dependsOn();

	public boolean isDone();

	public boolean canStart();
<<<<<<< Updated upstream

	public void process();

	public void configure(WebHookRequestJson requestJson, MetaModelRepresentation represntation);

=======
	public void init(WebHookRequestJson request, MetaModelRepresntation representation);
>>>>>>> Stashed changes
}
