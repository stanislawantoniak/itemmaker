package pl.essay.languages;

import java.util.List;

public class Languages {

	private List<Language> languages;
	
	public void init(){
		for (Language l : this.languages){
			if (l != this.getDefaultLanguage()) //comparing objects and that is ok!
				l.addTranslations(this.getDefaultLanguage()); //add translations form default ie english 
		}
	}
	
	public void setLanguages(List<Language> x){
		this.languages = x;
	}
	
	public List<Language> getLanguages(){
		return this.languages;
	}
			
	public Language getLanguage(String l){
		for (Language lang: languages)
			if (l.equals(lang.getAcronym())) 
					return lang; 
		return this.getDefaultLanguage();
	}
	public Language getDefaultLanguage(){
		return languages.get(0); //looks like hardcode - but sufficient for the demo
	}
}
