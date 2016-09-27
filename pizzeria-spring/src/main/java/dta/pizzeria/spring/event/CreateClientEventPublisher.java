package dta.pizzeria.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class CreateClientEventPublisher implements ApplicationEventPublisherAware{

	private ApplicationEventPublisher publisher;
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
		
	}
	
	public void publish(){
		CreateClientEvent cce = new CreateClientEvent(this);
		publisher.publishEvent(cce);
	}

}
