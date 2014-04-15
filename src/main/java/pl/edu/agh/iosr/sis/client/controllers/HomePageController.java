package pl.edu.agh.iosr.sis.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

	@Autowired
	private ControllerCommons controllerCommons;

	@RequestMapping({ "/", "/index" })
	public ModelAndView index() {
		return controllerCommons.createMAV("index");
	}

	@RequestMapping("/error")
	public ModelAndView error() {
		return new ModelAndView("error");
	}
}
