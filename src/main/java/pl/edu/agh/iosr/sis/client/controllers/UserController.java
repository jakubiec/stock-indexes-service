package pl.edu.agh.iosr.sis.client.controllers;

import javax.validation.Valid;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.iosr.sis.client.commands.UserCommand;
import pl.edu.agh.iosr.sis.core.daos.UserDAO;
import pl.edu.agh.iosr.sis.core.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String SORTING_FIELD = "login";

	private static final int USERS_PER_PAGE = 10;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	@Autowired
	private ControllerCommons controllerCommons;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@Valid UserCommand user, BindingResult result) {
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

	@RequestMapping("/create/form")
	public ModelAndView createForm() {
		ModelMap model = new ModelMap();
		model.addAttribute("userCommand", new UserCommand());
		return new ModelAndView("signUp", model);
	}

	@RequestMapping(value = "/users/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView users(@PathVariable Integer pageNumber) {
		ModelAndView mav = controllerCommons.createMAV("users");

		PageRequest request = new PageRequest(pageNumber - 1, USERS_PER_PAGE, Sort.Direction.ASC, SORTING_FIELD);
		Page<User> page = userDAO.findAll(request);

		int currentPage = page.getNumber() + 1;
		int begin = Math.max(1, currentPage - 3);
		int end = Math.min(begin + 6, page.getTotalPages());

		mav.addObject("page", page);
		mav.addObject("beginIndex", begin);
		mav.addObject("currentIndex", currentPage);
		mav.addObject("endIndex", end);

		return mav;
	}
	
	@RequestMapping("/edit/{userId}")
	public ModelAndView edit(@PathVariable Long userId) {
		User user = userDAO.findById(userId);
		
		if (user == null) {
			return controllerCommons.createMAV("index");
		}
		
		ModelAndView mav = controllerCommons.createMAV("editUser");
		mav.addObject("command", user);
		return mav;
	}
}
