package pl.essay.Session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {
	private String cookie;
	private String languageSelected;

	public void setCookie(String c){
		this.cookie = c;
	}
	public String getCookie(){
		return this.cookie;
	}
	public void setLanguageSelected(String c){
		this.languageSelected = c;
	}
	public String getLanguageSelected(){
		return this.languageSelected;
	}
}
