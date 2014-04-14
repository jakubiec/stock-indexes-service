package pl.edu.agh.iosr.sis.client.services;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.iosr.sis.core.entities.IndexValue;

public class IndexConsumer {
	
	@Autowired
	private IndexDispatcher indexDispatcherRepository;
	
	public void receive(IndexValue indexValue){
		indexDispatcherRepository.fireNewIndexValue(indexValue);
		System.out.println("Index consumer get index: "+indexValue.getSymbol() +" "+indexValue.getValue());
	}
}
