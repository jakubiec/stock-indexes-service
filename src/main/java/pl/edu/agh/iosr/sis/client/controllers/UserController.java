package pl.edu.agh.iosr.sis.client.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.iosr.sis.client.commands.UserCommand;
import pl.edu.agh.iosr.sis.client.validators.UserValidator;
import pl.edu.agh.iosr.sis.core.daos.IndexDAO;
import pl.edu.agh.iosr.sis.core.daos.UserDAO;
import pl.edu.agh.iosr.sis.core.entities.Index;
import pl.edu.agh.iosr.sis.core.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String SORTING_FIELD = "login";

	private static final int USERS_PER_PAGE = 10;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private IndexDAO indexDAO;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	@Autowired
	private ControllerCommons controllerCommons;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("command") UserCommand user, BindingResult result) {
		UserValidator validator = new UserValidator();
		validator.validate(user, result);

		if ( result.hasErrors() ) {
			return new ModelAndView("signUp", result.getModel());
		}

		if ( userDAO.findByLogin(user.getLogin()) != null ) {
			ModelAndView mav = new ModelAndView("signUp", result.getModel());
			mav.addObject("userExists", "User " + user.getLogin() + " already exists!");
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
		model.addAttribute("command", createCommand(null));
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

		if ( user == null ) {
			return controllerCommons.createMAV("index");
		}

		ModelAndView mav = controllerCommons.createMAV("editUser");
		mav.addObject("command", createCommand(user));
		mav.addObject("allIndexes", getAllIndexes());
		mav.addObject("userId", userId);
		return mav;
	}

	@RequestMapping("/update/{userId}")
	public ModelAndView update(@ModelAttribute("command") UserCommand command, @PathVariable Long userId, BindingResult result) {
		UserValidator validator = new UserValidator();
		validator.validate(command, result);

		if ( result.hasErrors() ) {
			ModelAndView mav = controllerCommons.createMAV("editUser");
			mav.addAllObjects(result.getModel());
			mav.addObject("allIndexes", getAllIndexes());
			mav.addObject("userId", userId);
			return mav;
		}

		User user = userDAO.findById(userId);

		if ( user != null ) {
			updateUserValues(user, command);
			userDAO.save(user);
		}

		ModelAndView mav = controllerCommons.createMAV("redirect:/user/users/1");
		return mav;
	}

	@RequestMapping("/delete/{userId}")
	public ModelAndView delete(@PathVariable Long userId) {
		User user = userDAO.findById(userId);

		if ( user != null ) {
			userDAO.delete(user);
		}

		ModelAndView mav = controllerCommons.createMAV("redirect:/user/users/1");
		return mav;
	}

	private Map<String, String> getAllIndexes() {
		Map<String, String> indexes = new HashMap<>();
		for ( Index index : indexDAO.findAll() ) {
			indexes.put(index.getSymbol(), index.getSymbol());
		}
		return indexes;
	}

	private UserCommand createCommand(User user) {
		if ( user == null ) {
			return new UserCommand(true);
		}

		UserCommand command = new UserCommand(false);
		command.setLogin(user.getLogin());
		command.setIsAdmin(user.getIsAdmin());

		Set<Index> userIndexes = user.getAvailableIndexes();
		List<String> indexes = new ArrayList<>(userIndexes.size());
		for ( Index index : userIndexes ) {
			indexes.add(index.getSymbol());
		}

		command.setIndexes(indexes);

		return command;
	}

	private void updateUserValues(User user, UserCommand command) {
		user.setLogin(command.getLogin());
		user.setIsAdmin(command.getIsAdmin());

		String password = StringUtils.trimToEmpty(command.getPassword());
		if ( password.length() > 0 ) {
			String encryptedPassword = passwordEncryptor.encryptPassword(password);
			user.setPassword(encryptedPassword);
		}

		user.clearAvailableIndexes();

		List<String> chosenIndex = command.getIndexes();
		if ( CollectionUtils.isNotEmpty(chosenIndex) ) {
			List<Index> allIndexes = indexDAO.findAll();

			for ( String symbol : chosenIndex ) {
				boolean found = false;

				for ( Iterator<Index> i = allIndexes.iterator() ; i.hasNext() && !found ; ) {
					Index index = i.next();

					if ( index.getSymbol().equals(symbol) ) {
						user.addAvailableIndex(index);
						found = true;
					}
				}
			}
		}
	}
}
