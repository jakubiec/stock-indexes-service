package pl.edu.agh.iosr.sis.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@Autowired
	private ControllerCommons controllerCommons;

	@RequestMapping("/denied")
	public ModelAndView denied() {
		return controllerCommons.createMAV("denied");
	}
}
