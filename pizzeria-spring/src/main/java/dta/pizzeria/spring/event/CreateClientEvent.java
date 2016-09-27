package dta.pizzeria.spring.event;

import org.springframework.context.ApplicationEvent;

public class CreateClientEvent extends ApplicationEvent{

	public CreateClientEvent(Object source) {
		super(source);
	}

}
