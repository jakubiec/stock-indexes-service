package pl.edu.agh.iosr.sis.provider.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.sis.core.entities.IndexValue;



/**
 * Simple JMS producer. Sending indexes info to jms indexesQueue.
 * 
 * @author konrad
 * 
 */
@Component
public class IndexProducer {
	
	
	private JmsTemplate jmsTemplate;
	
	/**
	 * Sends indexes value to indexesQueue
	 * @param indexValues
	 */
	public void sendIndexInfo(List<IndexValue> indexValues) {
		for(IndexValue iv : indexValues)
			jmsTemplate.convertAndSend(iv);
		System.out.println("IndexProducer sent indexes\n");
	}
	
	@Autowired
	@Qualifier(value="jmsQueueTemplate")
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	
}
