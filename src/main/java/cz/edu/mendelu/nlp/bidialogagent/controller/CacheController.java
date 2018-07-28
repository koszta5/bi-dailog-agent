package cz.edu.mendelu.nlp.bidialogagent.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.edu.mendelu.nlp.bidialogagent.dialogflow.cache.service.CacheService;
@RestController
@RequestMapping("/cache")
public class CacheController {
	
	@Autowired
	private CacheService service;
	
	
	@RequestMapping("list")
	public List<String> listCaches (){
		return service.listCaches();
	}
	@RequestMapping("listCache")
	public Map<String, ?> listCache(String cacheName) {
		return service.listCache(cacheName);
	}
	
	@RequestMapping("refresh")
	public Boolean refresh (String cacheName){
			return service.refresh(cacheName);
		
	}
	
	
	


}
