package pl.edu.agh.iosr.sis.provider.schedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.iosr.sis.core.daos.IndexDAO;
import pl.edu.agh.iosr.sis.core.entities.Index;
import pl.edu.agh.iosr.sis.core.entities.IndexValue;
import pl.edu.agh.iosr.sis.provider.exceptions.DownloadException;
import pl.edu.agh.iosr.sis.provider.services.IndexProducer;
import pl.edu.agh.iosr.sis.provider.services.YahooFinanceDownloader;

public class ProduceStockIndexesDataTask {

	@Autowired
	private IndexDAO indexDAO;

	@Autowired
	private IndexProducer indexProducer;

	public void produce() {
		List<String> indexSymbols = getIndexSymbols();
		Map<String, BigDecimal> valuesMap;
		try {
			valuesMap = YahooFinanceDownloader.getIndexValue(indexSymbols
					.toArray(new String[indexSymbols.size()]));
			List<IndexValue> indexValues = getIndexValues(valuesMap);
			indexProducer.sendIndexInfo(indexValues);
		} catch (DownloadException e) {
			e.printStackTrace();
		}

	}

	private List<IndexValue> getIndexValues(Map<String, BigDecimal> valuesMap) {
		Date now = getDate();
		List<IndexValue> values = new ArrayList<IndexValue>();
		IndexValue indexValue;
		for (String symbol : valuesMap.keySet()) {
			indexValue = new IndexValue();
			indexValue.setSymbol(symbol);
			indexValue.setValue(valuesMap.get(symbol));
			indexValue.setValueDate(now);
			values.add(indexValue);
		}
		return values;
	}

	private Date getDate() {
	
		return DateTime.now(DateTimeZone.forID("America/New_York")).toDate();
		
	}

	private List<String> getIndexSymbols() {
		List<String> symbols = new ArrayList<String>();
		for (Index indx : indexDAO.findAll())
			symbols.add(indx.getSymbol());
		return symbols;
	}
}
