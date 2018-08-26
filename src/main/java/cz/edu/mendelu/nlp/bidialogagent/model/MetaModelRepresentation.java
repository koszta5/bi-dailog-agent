package cz.edu.mendelu.nlp.bidialogagent.model;

import com.google.cloud.dialogflow.v2.WebhookRequest;

public class MetaModelRepresentation {

	
	private WebhookRequest originalRequest;
	private String entityType;
	private String queryType;
}
