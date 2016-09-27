package dta.pizzeria.spring.event;

import org.springframework.context.ApplicationListener;

public class CreateClientEventHandler implements ApplicationListener<CreateClientEvent>{

	@Override
	public void onApplicationEvent(CreateClientEvent cce) {
	}

}
