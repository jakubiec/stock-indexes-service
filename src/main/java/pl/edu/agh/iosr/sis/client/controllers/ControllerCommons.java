package pl.edu.agh.iosr.sis.client.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.iosr.sis.client.authentication.SecurityHelper;
import pl.edu.agh.iosr.sis.client.menu.MenuItem;

@Component
public class ControllerCommons {

	@Resource(name = "applicationMenu")
	private List<MenuItem> menuItems;

	public ModelAndView createMAV(String name) {
		ModelAndView mav = new ModelAndView(name);
		mav.addObject("menuItems", menuItems);
		mav.addObject("username", SecurityHelper.getUsername());
		return mav;
	}
}
