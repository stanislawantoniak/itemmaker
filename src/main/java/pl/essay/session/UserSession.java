package pl.essay.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import pl.essay.languages.Language;
import pl.essay.languages.Languages;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {
	private String cookie;
	@Autowired(required=true)
	@Qualifier(value="english")
	private Language languageSelected;
	
	@Autowired(required=true)
	@Qualifier(value="languages")
	private Languages languages;
		
	public UserSession(){
	}

	public void setCookie(String c){
		this.cookie = c;
	}
	public String getCookie(){
		return this.cookie;
	}
	public void setLanguageSelected(String c){
		this.languageSelected = this.languages.getLanguage(c);
	}
	public Language getLanguageSelected(){
		return this.languageSelected;
	}
}
