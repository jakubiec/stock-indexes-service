package pl.edu.agh.iosr.sis.client.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.iosr.sis.core.daos.UserDAO;
import pl.edu.agh.iosr.sis.core.entities.User;


@Controller
public class TestDatabaseController {

	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("/newUser")
	public ModelAndView createUser(@RequestParam String login, @RequestParam String pass, @RequestParam Boolean isAdmin) {
		User u = new User();
		u.setLogin(login);
		u.setPassword(pass);
		u.setIsAdmin(isAdmin);
		
		System.out.println("\n\nSaving user" + u);
		
		userDAO.save(u);
		
		System.out.println("\n\nUser Saved");
		
		return new ModelAndView("redirect:/users");
	}
	
	@RequestMapping("/users") 
	public ModelAndView listUsers() {
		ModelAndView mav = new ModelAndView("users");
		
		List<User> all = userDAO.findAll();
		System.out.println("\n\nShowing users: ");
		for (User user : all) {
			System.out.println(user.getLogin());
		}
		mav.addObject("users", all);
		
		return mav;
	}
}
