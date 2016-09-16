package pl.essay.itemMaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController extends BaseUserController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listItems(Model model) {
		System.out.println("from controller.main");
		return "main";
	}
}
