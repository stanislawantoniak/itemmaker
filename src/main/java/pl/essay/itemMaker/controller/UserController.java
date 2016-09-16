package pl.essay.itemMaker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.essay.Session.UserSession;


@Controller
public class UserController extends BaseUserController{

	protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

	 //with scope session
	@Autowired
	@Qualifier(value="userSession")
	private UserSession userSession;
	
	public void setUserSession(UserSession us){
		this.userSession = us;
	}
	public UserSession getUserSession(){
		return this.userSession;
	}
	
	@RequestMapping(value = "/selectLanguage/{languageSelected}", method = RequestMethod.GET)
	public String selectLanguage(@PathVariable String languageSelected, 
			@RequestHeader(value = "referer", required = false) final String referer, 
			Model model) {
		logger.info("language select");
		this.userSession.setLanguageSelected(languageSelected);
		return "redirect:"+referer;
	}
	
	protected void addUserSessionToModel(Model model){
		model.addAttribute("user", this.getUserSession());
	}
	
}
