package dta.pizzeria_spring.beans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dta.pizzeria.spring.SpringConsole;
import fr.pizzeria.service.Stockage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfig.class)
public class SpringBeansAnnotationTest {
	
	@Autowired
	private Stockage<?> pizzaStockage;
	
	@Autowired
	private SpringConsole sc;
	
	@Test
	public void testCreationBean(){
		pizzaStockage.findAll().forEach(System.out::println);
	}

	@Test
	public void testSpringConsole(){
		sc.afficherToutesLesPizzas();
	}
}
