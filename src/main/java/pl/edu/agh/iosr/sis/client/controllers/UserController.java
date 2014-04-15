package pl.edu.agh.iosr.sis.client.controllers;

import javax.validation.Valid;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.iosr.sis.client.commands.UserCommand;
import pl.edu.agh.iosr.sis.core.daos.UserDAO;
import pl.edu.agh.iosr.sis.core.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	@Autowired
	private ControllerCommons controllerCommons;

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView createUser(@Valid UserCommand user, BindingResult result) {
		if ( result.hasErrors() ) {
			return new ModelAndView("signUp", result.getModel());
		}

		if ( userDAO.findByLogin(user.getLogin()) != null ) {
			ModelAndView mav = new ModelAndView("signUp", result.getModel());
			mav.addObject("userExists", "User " + user.getLogin() + " already exists!;");
			return mav;
		}

		String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());

		User newUser = new User();
		newUser.setLogin(user.getLogin());
		newUser.setPassword(encryptedPassword);
		newUser.setIsAdmin(false);

		userDAO.save(newUser);

		return new ModelAndView("login");
	}

	@RequestMapping(value = "/showCreateUser")
	public ModelAndView showCreateUserView() {
		ModelMap model = new ModelMap();
		model.addAttribute("userCommand", new UserCommand());
		return new ModelAndView("signUp", model);
	}

}
