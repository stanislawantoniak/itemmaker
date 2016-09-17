package pl.essay.languages;

import java.util.Map;

public class Language {
	private String name;
	private String acronym;
	private String flag;
	
	private Translator translator;
	private Map<String, String> staticTexts; 
		
	public String getName(){
		return this.name;
	};
	public void setName(String l){
		this.name = l;
	};
	
	public String getAcronym(){
		return this.acronym;
	};
	public void setAcronym(String l){
		this.acronym = l;
	};
	public String getFlag(){
		return this.flag;
	};
	public void setFlag(String l){
		this.flag = l;
	};
	
	public Map<String, String> getStaticTexts(){
		return this.staticTexts;
	}
	public void setTranslator(Translator t){
		this.translator = t;
		this.staticTexts = this.translator.getTranslations();
	}
}
