package pl.edu.agh.iosr.sis.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping({ "/login", "/loginfailed", "/logout" })
	public ModelAndView login() {
		return new ModelAndView("login");
	}
}
