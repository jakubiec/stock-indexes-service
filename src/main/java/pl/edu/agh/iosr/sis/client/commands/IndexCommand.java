package pl.edu.agh.iosr.sis.client.commands;

import java.util.List;
import java.util.Map;

import pl.edu.agh.iosr.sis.core.entities.IndexValue;

public class IndexCommand {

	private String symbol;
	private Map<String, String> indexesMap;
	private List<IndexValue> indexValues;
	private String startDate;
	private String endDate;

	public List<IndexValue> getIndexValues() {
		return indexValues;
	}

	public void setIndexValues(List<IndexValue> indexValues) {
		this.indexValues = indexValues;
	}

	public Map<String, String> getIndexesMap() {
		return indexesMap;
	}

	public void setIndexesMap(Map<String, String> indexesMap) {
		this.indexesMap = indexesMap;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
