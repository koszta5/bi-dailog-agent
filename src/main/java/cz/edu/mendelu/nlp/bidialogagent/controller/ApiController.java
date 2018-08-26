package cz.edu.mendelu.nlp.bidialogagent.controller;

import cz.edu.mendelu.nlp.bidialogagent.chatfuel.builder.ChatFuelReplyBuilder;
import cz.edu.mendelu.nlp.bidialogagent.chatfuel.model.ChatFuelReply;
import cz.edu.mendelu.nlp.bidialogagent.chatfuel.model.button.Button;
import cz.edu.mendelu.nlp.bidialogagent.chatfuel.model.button.RedirectBlockButton;
import cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache.EntityTypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("/api")
public class ApiController {

	@Autowired
	private EntityTypeCache entityTypeCache;


	@RequestMapping("/listEntities")
	public ChatFuelReply getAvailableEntities (){
		List<Button> blocks = entityTypeCache.asMap()
											 .values()
											 .stream()
											 .map(entity -> new RedirectBlockButton(entity.getDisplayName() +"_BLOCK"))
											 .collect(Collectors.toList());
		ChatFuelReplyBuilder builder = new ChatFuelReplyBuilder().withButtons("Here are the types you can work with ", blocks);
		return builder.build();
	}
}
