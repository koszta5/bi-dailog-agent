package cz.edu.mendelu.nlp.bidialogagent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BiDialogConfiguration {
	@Value("${cloud.google.config.path}")
	private String propertiesForGoogleCloudProjectPath;
	
	@Bean
	public GCloudConfiguration googleSetup() {
		String projectId = System.getenv().get("GOOGLE_CLOUD_PROJECT");
		String credentialsPath = System.getenv().get("GOOGLE_APPLICATION_CREDENTIALS");
		if (projectId == null && credentialsPath == null){
			Properties props = loadSetupFromPropertiesFile();
			projectId = props.getProperty("GOOGLE_CLOUD_PROJECT");
			credentialsPath = props.getProperty("GOOGLE_APPLICATION_CREDENTIALS");
			System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", credentialsPath);
		}
		return new GCloudConfiguration(projectId, credentialsPath);
	}

	private Properties loadSetupFromPropertiesFile() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(propertiesForGoogleCloudProjectPath)));
			return props;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
