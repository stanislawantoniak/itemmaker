package pl.essay.itemMaker.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@Inject private UserService userService;

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
	public String updateUser(@Valid @ModelAttribute("user") UserForm userForm,  
			BindingResult result, //must follow modelattribute!!!!
			RedirectAttributes redirectAttributes,
			Model model){

		logger.info("update user data: "+userForm);

		if ( (result.hasErrors() && userForm.getId() == 0) //password must not be empty - no errors allowed for new user
				|| ( result.getFieldErrorCount() > 1 && userForm.getId() != 0) ) { //allow for 1 error which is no password for existing user
			this.addGenericDataToModel(model);
			//disable language selector - because still staying on the same url and it doesnt support RequestMethod.GET
			model.addAttribute("languageSelectorClass","disabled"); 
			model.addAttribute("user", userForm);
			return "users/userEdit";
		} else {
			User user;
			if (userForm.getId() == 0)
				user = new User();
			else
				user = this.userService.getUserById(userForm.getId());

			user.setUsername(userForm.getUsername());
			user.setEnabled(userForm.getEnabled());
			if (userForm.getRolesSelected() != null)
				System.out.println(userForm.getRolesSelected().toArray());
			user.setRoles(StringUtils.join(userForm.getRolesSelected().toArray(),";"));
			if (!"".equals(userForm.getPassword())) //set pass only if filled passwodr field on form
				user.setPassword(userForm.getPassword());
			if (userForm.getId() == 0)
				this.userService.addUser(user);
			else 
				this.userService.updateUser(user);
		}
		return  "redirect:/users" ;
	}


	//edit existing and new user
	//fill data model with generic and user data
	//in case path id is == 0 then create new user for edit 
	@RequestMapping("/users/user/edit/{id}")
	public String editUser(@PathVariable("id") int id, Model model){
		


		this.addGenericDataToModel(model);

		UserForm uf = new UserForm( (id != 0 ? this.userService.getUserById(id) : new User()) );//init user for with new user or get from db 

		model.addAttribute("user", uf);

		return "users/userEdit";
	}
	
}
