package cz.edu.mendelu.nlp.bidialogagent.dialogflow.webhook.archive;

import cz.edu.mendelu.nlp.dialogflow.WebHookRequestJson;

public interface RequestArchiver extends Runnable {

	public void archive(WebHookRequestJson request);
}
