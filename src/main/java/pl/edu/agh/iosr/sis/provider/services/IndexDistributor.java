package pl.edu.agh.iosr.sis.provider.services;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import pl.edu.agh.iosr.sis.core.daos.IndexValueDAO;
import pl.edu.agh.iosr.sis.core.entities.IndexValue;

/**
 * Dispatcher for index values form indexesQueue.
 * @author konrad
 *
 */
public class IndexDistributor {
	
	
	@Autowired
	private IndexValueDAO indexValueDAO;
	
	private JmsTemplate jmsTemplate;
	/**
	 * Receive index values from indexesQueue, persist them and send to indexesTopic.
	 * @param indexValue
	 */
	public void receiveAndSend(final IndexValue indexValue){
		indexValueDAO.save(indexValue);
		jmsTemplate.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage(indexValue);
				message.setStringProperty("symbol", indexValue.getSymbol());
				return message;
			}
		});
		System.out.println("IndexDistributor received and resend indexes");
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	
}
