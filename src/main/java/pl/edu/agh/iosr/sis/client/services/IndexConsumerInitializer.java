package pl.edu.agh.iosr.sis.client.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.sis.core.daos.IndexDAO;
import pl.edu.agh.iosr.sis.core.entities.Index;

@Component
@Scope("singleton")
@Lazy(value = false)
public class IndexConsumerInitializer {

	@Autowired
	private IndexDAO indexDAO;

	@Autowired
	@Qualifier("jmsTopicContainer")
	private ObjectFactory<DefaultMessageListenerContainer>  topicContainerBeanFactory;
	
	private List<DefaultMessageListenerContainer> containers = new ArrayList<DefaultMessageListenerContainer>();

	@PostConstruct
	private void init() throws Exception {
		List<Index> indexes = indexDAO.findAll();
		for (Index indx : indexes) {
			DefaultMessageListenerContainer container = topicContainerBeanFactory.getObject();
			container.setMessageSelector("symbol = '" + indx.getSymbol() + "'");
			container.start();
			containers.add(container);
		}
	}
	
	@PreDestroy
	private void destroy(){
		for(DefaultMessageListenerContainer container : containers){
			container.stop();
			container.destroy();
		}
		
	}
}
