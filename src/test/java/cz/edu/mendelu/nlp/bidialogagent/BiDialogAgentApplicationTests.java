package cz.edu.mendelu.nlp.bidialogagent;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BiDialogAgentApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void loadAgentProps(){
		String projectId = System.getenv().get("GOOGLE_CLOUD_PROJECT");
		String credentialsPath = System.getenv().get("GOOGLE_APPLICATION_CREDENTIALS");
		if (projectId == null && credentialsPath == null){
			Properties props = loadSetupFromPropertiesFile();
			projectId = props.getProperty("GOOGLE_CLOUD_PROJECT");
			credentialsPath = props.getProperty("GOOGLE_APPLICATION_CREDENTIALS");
			System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", credentialsPath);
		}
		new GCloudConfiguration(projectId, credentialsPath);
	}
	
	private Properties loadSetupFromPropertiesFile() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File("/Volumes/Kosta_Home/homes/kosta/googleProject.properties")));
			return props;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
