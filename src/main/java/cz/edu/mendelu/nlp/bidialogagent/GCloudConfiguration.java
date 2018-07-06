package cz.edu.mendelu.nlp.bidialogagent;

public class GCloudConfiguration {

	private String projectId;
	private String credentialsPath;
	
	public GCloudConfiguration(String projectId, String credentialsPath) {
		super();
		this.projectId = projectId;
		this.credentialsPath = credentialsPath;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCredentialsPath() {
		return credentialsPath;
	}
	public void setCredentialsPath(String credentialsPath) {
		this.credentialsPath = credentialsPath;
	}
	
	
}
