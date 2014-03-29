package pl.edu.agh.iosr.sis.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

	@RequestMapping({"/", "/index"})
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}
	
	@RequestMapping("/error")
	public ModelAndView error() {
		return new ModelAndView("error");
	}
}
