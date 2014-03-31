package pl.edu.agh.iosr.sis.client.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "TestMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/myQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TestMDB implements MessageListener {

	@Override
	public void onMessage(Message rcvMessage) {
		TextMessage message = null;

		try {
			if (rcvMessage instanceof TextMessage) {
				message = (TextMessage) rcvMessage;
				
				// do some stuff
				
				System.out.println("\nReceived:\t" + message.getText()
						+ "\n");
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

	}

}
