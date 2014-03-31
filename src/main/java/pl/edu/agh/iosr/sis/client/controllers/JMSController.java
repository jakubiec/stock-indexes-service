package pl.edu.agh.iosr.sis.client.controllers;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JMSController {
	
	private static int cnt = 0;
	
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName = "java:/queue/myQueue")
	private Queue queue;
	
	@RequestMapping("/jmsExample")
	public ModelAndView jmsExample() {
		ModelAndView mav = new ModelAndView("jms"); 
		Connection connection = null;
		Session session = null;
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer producer = session.createProducer(queue);
			connection.start();
			
			TextMessage message = session.createTextMessage();
			message.setText("Message text");
			producer.send(message);
			
			mav.addObject("cnt", ++cnt);
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		
		return mav;
	}
}
