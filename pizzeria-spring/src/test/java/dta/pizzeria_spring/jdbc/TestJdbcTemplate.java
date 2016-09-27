package dta.pizzeria_spring.jdbc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dta.pizzeria.spring.PizzaJdbc;
import dta.pizzeria_spring.beans.SpringConfig;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestJdbcTemplate {
	
	@Autowired PizzaJdbc stockagePizza;
	
	@Test
	public void recupPizza(){
		stockagePizza.findAll().forEach(System.out::println);
	}
	
	@Test
	public void savePizzaTest(){
		Pizza maPizza = new Pizza("MER","Merguez",12.00);
		stockagePizza.saveTobject(maPizza);
	}
	
	@Test 
	public void updatePizzaTest(){
		List<Pizza> lst = (List<Pizza>) stockagePizza.findAll();
		Pizza maPizza = lst.get(0);
		maPizza.setCode("BRO");
		stockagePizza.updateTobject(maPizza, null);
	} 
	
	@Test
	public void deletePizza(){
		List<Pizza> lst = (List<Pizza>) stockagePizza.findAll();
		Pizza maPizza = lst.get(0);
		stockagePizza.deleteTobject(maPizza.getCode());
	}

}
