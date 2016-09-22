package dta.pizzeria_spring;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import junit.framework.Assert;

public class SpringBeansXmlTest {

	@Test
	public void testCreationBean() throws InterruptedException {
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml")) {

			Stockage<?> pizzaStockage = context.getBean(Stockage.class);

			pizzaStockage.findAll().forEach(System.out::println);
		}
	}
	
	@Test
	public void testPizza(){
		try
			(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-config.xml")){;
			Pizza maPizza = context.getBean(Pizza.class);
			Assert.assertEquals("TST", maPizza.getCode());
		}
	}
}
