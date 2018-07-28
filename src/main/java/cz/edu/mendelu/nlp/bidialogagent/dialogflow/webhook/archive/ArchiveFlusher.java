package cz.edu.mendelu.nlp.bidialogagent.dialogflow.webhook.archive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ArchiveFlusher {

	private List<RequestArchiver> archivers;
	private TaskExecutor springTaskExecutor;
	
	@Autowired
	public ArchiveFlusher(TaskExecutor springTaskExecutor) {
		super();
		this.springTaskExecutor = springTaskExecutor;
	}




	@Autowired
	public void setArchivers(List<RequestArchiver> archivers) {
		this.archivers = archivers;
	}

	


	@Scheduled(fixedRate=60000)
	public void flushArchives(){
		archivers
			.stream()
			.forEach(springTaskExecutor::execute);
	}
}
