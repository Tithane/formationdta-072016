package dta.pizzeria_spring.transaction;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import dta.pizzeria.spring.PizzaJdbc;
import dta.pizzeria.spring.StockagePizzaParLot;
import dta.pizzeria_spring.beans.SpringConfig;
import fr.pizzeria.model.Pizza;

@Configuration
@ComponentScan({ "dta.pizzeria_spring.transaction" })
public class SpringJdbcTransactionTest {
	
	@Autowired StockagePizzaParLot stockageParLot;
	@Autowired PizzaJdbc stockagePizza;
	
	@Test
	public void insertionLotPizza(){
		
		List<Pizza> maListe = new ArrayList<>();
		maListe.add(new Pizza("ETE"));
		maListe.add(new Pizza("ETA"));
		maListe.add(new Pizza("ETI"));
		maListe.add(new Pizza("ETO"));
		maListe.add(new Pizza("ETU"));
		maListe.add(new Pizza("ETYF"));
		maListe.add(new Pizza("ETG"));
		maListe.add(new Pizza("ETK"));
		maListe.add(new Pizza("ETL"));
		maListe.add(new Pizza("ETM"));
		
		
		stockageParLot.insererPizzaParLot(maListe, 3);
		
	}
		

}
