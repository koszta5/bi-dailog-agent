package cz.edu.mendelu.nlp.bidialogagent.dialogflow.webhook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.cloud.dialogflow.v2.WebhookRequest;
import com.google.cloud.dialogflow.v2.WebhookResponse;

@Controller
@RequestMapping("/webhook")
public class WebhookController {

	@RequestMapping("/reply")
	public @ResponseBody WebhookResponse webHookReply(WebhookRequest webhookRequest) {
		
	}
}
