package pl.edu.agh.iosr.sis.client.listeners;

import pl.edu.agh.iosr.sis.core.entities.IndexValue;

public abstract class NewIndexValueListener {

	private String indexSymbol;
	
	public NewIndexValueListener(String indexSymbol){
		this.indexSymbol = indexSymbol;
	}

	public String getIndexSymbol() {
		return indexSymbol;
	}
	
	public abstract void onNewIndexValue(IndexValue newIndexValue);
}
