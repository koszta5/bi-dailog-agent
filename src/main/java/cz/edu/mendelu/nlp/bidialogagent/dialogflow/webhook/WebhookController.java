package cz.edu.mendelu.nlp.bidialogagent.dialogflow.webhook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.edu.mendelu.nlp.bidialogagent.dialogflow.webhook.archive.RequestArchiver;
import cz.edu.mendelu.nlp.dialogflow.WebHookRequestJson;
import cz.edu.mendelu.nlp.dialogflow.WebHookResponseJson;
import net.bull.javamelody.MonitoredWithSpring;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
	
	@Autowired
	private RequestArchiver archiver;
	
	
	@RequestMapping("/reply")
	public WebHookResponseJson webHookReply( @RequestBody WebHookRequestJson request) throws Exception {
		archiver.archive(request);
		System.out.println("Text from DIALOGFLOW --> "+request.getQueryResult().getQueryText());
		WebHookResponseJson response =  new WebHookResponseJson();
		String sampleReply = "ECHO FROM JAVA: "+request.getQueryResult().getQueryText();
		System.out.println("Replying with "+sampleReply);
		response.setFulfillmentText(sampleReply);
		return response;
	}

	
}
