package pl.edu.agh.iosr.sis.client.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.agh.iosr.sis.core.daos.IndexDAO;
import pl.edu.agh.iosr.sis.core.daos.IndexValueDAO;
import pl.edu.agh.iosr.sis.core.entities.IndexValue;
import pl.edu.agh.iosr.sis.provider.services.IndexProducer;

@Controller
public class IndexesController {
	
	@Autowired
	private IndexDAO indexDAO;
	
	@Autowired
	private IndexValueDAO indexValueDAO;
	
	@Autowired
	private IndexProducer indexProducer;
	
	@RequestMapping("/indexes")
	public ModelAndView indexes(){
		jmsTest();
		ModelAndView mv = new ModelAndView("indexes");
		mv.addObject("indexesList", indexDAO.findAll());
		return mv;
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
	
	@RequestMapping(value="/chooseIndex", method = RequestMethod.POST)
	public ModelAndView chooseIndex(@RequestParam String symbol) {
		return null;
	}

	
}
