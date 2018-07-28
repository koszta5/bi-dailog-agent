package cz.edu.mendelu.nlp.bidialogagent.model;

import java.util.List;


public class QueryType {
	private String displayName;
	private List<String> synonyms;
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<String> getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}
	
	

}
