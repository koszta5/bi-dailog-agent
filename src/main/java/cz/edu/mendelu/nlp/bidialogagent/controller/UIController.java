package cz.edu.mendelu.nlp.bidialogagent.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {
	
	@RequestMapping("/")
	public String home(Map <String, Object> model){
		return "home";
	}
	
	@RequestMapping("/settings")
	public String setttings(Map <String, Object> model){
		return "settings";
	}
	
	@RequestMapping("/javaMelody")
	public String javaMelody(Map <String, Object> model){
		return "javaMelody";
	}
	

}
