package pl.edu.agh.iosr.sis.client.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.iosr.sis.client.authentication.SecurityHelper;
import pl.edu.agh.iosr.sis.client.commands.IndexCommand;
import pl.edu.agh.iosr.sis.core.daos.IndexValueDAO;
import pl.edu.agh.iosr.sis.core.daos.UserDAO;
import pl.edu.agh.iosr.sis.core.entities.Index;
import pl.edu.agh.iosr.sis.core.entities.User;
import pl.edu.agh.iosr.sis.provider.services.IndexProducer;

@Controller
@RequestMapping("/indexes")
public class IndexesController {

	private static final String LAST_HOUR = " 23:59:59";
	private static final String FIRST_HOUR = " 00:00:00";
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private IndexValueDAO indexValueDAO;

	@Autowired
	private IndexProducer indexProducer;

	@Autowired
	private ControllerCommons controllerCommons;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexes() {
		IndexCommand indexCommand = new IndexCommand();
		indexCommand.setIndexesMap(createMap());
		ModelAndView mv = controllerCommons.createMAV("indexes");
		mv.addObject("indexCommand", indexCommand);
		return mv;
	}

	private Map<String, String> createMap() {
		Map<String, String> map = new HashMap<String, String>();

		String login = SecurityHelper.getUsername();
		User user = userDAO.findByLogin(login);
		Set<Index> indexes = user == null ? null : user.getAvailableIndexes();

		if ( CollectionUtils.isNotEmpty(indexes) ) {
			for ( Index indx : indexes )
				map.put(indx.getSymbol(), indx.getSymbol());
		}

		return map;
	}

	@RequestMapping(value = "/values", method = RequestMethod.POST)
	public ModelAndView chooseIndexPost(@ModelAttribute IndexCommand indexCommand, BindingResult bindingResult)
			throws ParseException {
		indexCommand.setIndexesMap(createMap());
		setDates(indexCommand);
		indexCommand.setIndexValues(indexValueDAO.findInPeriod(indexCommand.getSymbol(),
				parse(indexCommand.getStartDate(), FIRST_HOUR), parse(indexCommand.getEndDate(), LAST_HOUR)));
		ModelAndView mv = controllerCommons.createMAV("indexes");
		mv.addAllObjects(bindingResult.getModel());

		return mv;
	}

	private void setDates(IndexCommand indexCommand) throws ParseException {
		if ( indexCommand.getStartDate() == null ) {
			Date date = indexValueDAO.getFirstDate(indexCommand.getSymbol());
			if ( date != null ) {
				indexCommand.setStartDate(format(date));
			}
		}

		if ( indexCommand.getEndDate() == null ) {
			Date date = indexValueDAO.getLastDate(indexCommand.getSymbol());
			if ( date != null ) {
				indexCommand.setEndDate(format(date));
			}
		}
	}

	@RequestMapping(value = "/values", method = RequestMethod.GET)
	public ModelAndView chooseIndexGet(@ModelAttribute IndexCommand indexCommand, BindingResult bindingResult) {

		return new ModelAndView("error");
	}

	private Date parse(String stringDate, String time) throws ParseException {
		return DATE_TIME_FORMAT.parse(stringDate + time);
	}

	private String format(Date date) {

		return DATE_FORMAT.format(date);
	}

}
