package pl.essay.itemMaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import pl.essay.languages.Languages;
import pl.essay.session.UserSession;

@Controller
public class BaseController {

	//with scope session
	@Autowired
	protected UserSession userSession;
	
	@Autowired
	protected Languages languages; 
	
	public void setUserSession(UserSession us){
		this.userSession = us;
	}
	public UserSession getUserSession(){
		return this.userSession;
	}

	protected void addGenericDataToModel(Model model){
		model.addAttribute("user", this.getUserSession());
		model.addAttribute("languages", this.languages.getLanguages());
		model.addAttribute("__static__", this.userSession.getLanguageSelected().getTranslator().getTranslations());
	}

}
