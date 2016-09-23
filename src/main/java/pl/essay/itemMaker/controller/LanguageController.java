package pl.essay.itemMaker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LanguageController extends BaseController{

	protected static final Logger logger = LoggerFactory.getLogger(LanguageController.class);

	@RequestMapping(value = "/selectLanguage/{languageSelected}", method = RequestMethod.GET)
	public String selectLanguage(@PathVariable String languageSelected, 
			@RequestHeader(value = "referer", required = false) final String referer, 
			Model model) {
		this.userSession.setLanguageSelected(languageSelected);
		logger.info("language selected: "+this.userSession.getLanguageSelected().getName());
		return "redirect:"+referer;
	}
	
	
}
