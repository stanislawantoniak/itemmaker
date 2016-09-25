package pl.essay.itemMaker.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.essay.itemMaker.model.User;
import pl.essay.itemMaker.service.UserService;

@Controller
public class UserController extends BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	public void setUserService(UserService us){
		this.userService = us;
	} 

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model) {

		this.addGenericDataToModel(model);

		List<User> theList = (List<User>) this.userService.listUsers();
		logger.info("list size: "+theList.size());

		model.addAttribute("usersList", theList);

		return "users/userList";
	}

	//update model from edit view 
	//language selector is disabled
	//empty password => password not changed
	//password must not be empty for new user however 
	@RequestMapping(value= "/users/user/update", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("user") User user,  
			BindingResult result, //must follow modelattribute!!!!
			RedirectAttributes redirectAttributes,
			Model model){
		
		logger.info("update user data: "+user);
		
		if ( (result.hasErrors() && user.getId() == 0) //password must not be empty - no errors allowed for new user
			|| ( result.getFieldErrorCount() > 1 && user.getId() != 0) ) { //allow for 1 error which is no password for existing user
			this.addGenericDataToModel(model);
			//disable language selector - because still staying on the same url and it doesnt support RequestMethod.GET
			model.addAttribute("languageSelectorClass","disabled"); 
			model.addAttribute("user", user);
			return "users/userEdit";
		} else {
			if (user.getId() == 0)
				this.userService.addUser(user);
			else {
				if ("".equals(user.getPassword())){ //password not changed - get old pass
					User u = this.userService.getUserById(user.getId());
					user.setPassword(u.getPassword());
				}
				this.userService.updateUser(user);
			}
			return  "redirect:/users" ;
		}
	}

	//edit existing and new user
	//fill data model with generic and user data
	//in case path id is == 0 then create new user for edit 
	@RequestMapping("/users/user/edit/{id}")
	public String editUser(@PathVariable("id") int id, Model model){

		this.addGenericDataToModel(model);

		User user = (id != 0 ? this.userService.getUserById(id) : new User());
		user.setPassword("");//no password on edit form allowed

		model.addAttribute("user", user);
		
		return "users/userEdit";
	}
}
