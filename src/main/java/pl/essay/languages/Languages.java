package pl.essay.languages;

import java.util.List;

public class Languages {

	private List<Language> languages;
	
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
		return languages.get(0);
	}
}
