package pl.essay.itemMaker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import pl.essay.Session.UserSession;

public class BaseUserController {

	protected static final Logger logger = LoggerFactory.getLogger(BaseUserController.class);

	 //with scope session
	@Autowired
	private UserSession userSession;
	
	public void setUserSession(UserSession us){
		this.userSession = us;
	}
	public UserSession getUserSession(){
		return this.userSession;
	}

	protected void addUserSessionToModel(Model model){
		model.addAttribute("user", this.getUserSession());
	}
	
}
