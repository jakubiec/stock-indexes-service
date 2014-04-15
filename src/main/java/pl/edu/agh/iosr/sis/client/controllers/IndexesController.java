package pl.edu.agh.iosr.sis.client.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.iosr.sis.client.commands.IndexCommand;
import pl.edu.agh.iosr.sis.core.daos.IndexDAO;
import pl.edu.agh.iosr.sis.core.daos.IndexValueDAO;
import pl.edu.agh.iosr.sis.core.entities.Index;
import pl.edu.agh.iosr.sis.core.entities.IndexValue;
import pl.edu.agh.iosr.sis.provider.services.IndexProducer;

@Controller
@RequestMapping("/indexes")
public class IndexesController {

	@Autowired
	private IndexDAO indexDAO;

	@Autowired
	private IndexValueDAO indexValueDAO;

	@Autowired
	private IndexProducer indexProducer;

	@Autowired
	private ControllerCommons controllerCommons;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexes() {
		// jmsTest();
		IndexCommand indexCommand = new IndexCommand();
		indexCommand.setIndexesMap(createMap());
		ModelAndView mv = controllerCommons.createMAV("indexes");
		mv.addObject("indexCommand", indexCommand);
		return mv;
	}

	private Map<String, String> createMap() {
		Map<String, String> map = new HashMap<String, String>();
		for ( Index indx : indexDAO.findAll() )
			map.put(indx.getSymbol(), indx.getSymbol());
		return map;
	}

	private void jmsTest() {
		List<IndexValue> sampleValues = new ArrayList<IndexValue>();
		IndexValue indexValue = new IndexValue();
		indexValue.setSymbol("GOOG");
		indexValue.setValue(BigDecimal.TEN);
		indexValue.setValueDate(new Date());
		sampleValues.add(indexValue);
		indexProducer.sendIndexInfo(sampleValues);
	}

	@RequestMapping(value = "/values", method = RequestMethod.POST)
	public ModelAndView chooseIndex(@ModelAttribute IndexCommand indexCommand, BindingResult bindingResult) {
		indexCommand.setIndexesMap(createMap());
		indexCommand.setIndexValues(indexValueDAO.findBySymbol(indexCommand.getSymbol()));
		ModelAndView mv = controllerCommons.createMAV("indexes");
		mv.addObject("indexCommand", indexCommand);
		return mv;
	}

}
