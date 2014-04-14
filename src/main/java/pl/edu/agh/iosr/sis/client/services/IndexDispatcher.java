package pl.edu.agh.iosr.sis.client.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.sis.client.listeners.NewIndexValueListener;
import pl.edu.agh.iosr.sis.core.daos.IndexDAO;
import pl.edu.agh.iosr.sis.core.entities.Index;
import pl.edu.agh.iosr.sis.core.entities.IndexValue;

@Component
@Scope("singleton")
public class IndexDispatcher {

	@Autowired
	private IndexDAO indexDAO;

	private Map<String, List<NewIndexValueListener>> indexListeners;

	@PostConstruct
	private void init() {
		indexListeners = new HashMap<String, List<NewIndexValueListener>>();
		for (Index indx : indexDAO.findAll()) {
			indexListeners.put(indx.getSymbol(),
					new ArrayList<NewIndexValueListener>());
		}
	}
	
	public void fireNewIndexValue(IndexValue indexValue){
		for(NewIndexValueListener listener : indexListeners.get(indexValue.getSymbol()))
				listener.onNewIndexValue(indexValue);
	}
	
	public void addNewIndexValueListener(NewIndexValueListener listener){
		indexListeners.get(listener.getIndexSymbol()).add(listener);
	}
	
	public void removeNewIndexValueListener(NewIndexValueListener listener){
		indexListeners.get(listener.getIndexSymbol()).remove(listener);
	}
}
