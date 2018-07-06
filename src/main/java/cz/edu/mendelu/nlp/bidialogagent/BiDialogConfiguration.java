package cz.edu.mendelu.nlp.bidialogagent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BiDialogConfiguration {
	
	@Bean
	public GCloudConfiguration googleSetup() {
		String projectId = System.getenv().get("GOOGLE_CLOUD_PROJECT");
		String credentialsPath = System.getenv().get("GOOGLE_APPLICATION_CREDENTIALS");
		return new GCloudConfiguration(projectId, credentialsPath);
	}
	
	

}
