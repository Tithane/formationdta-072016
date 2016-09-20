package dta;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;

import fr.pizzeria.service.StockagePizzaJPA;



public class ServiceConfig {

	@Produces
	@ApplicationScoped
	public Stockage<Pizza> getStockagePizza(){
		return new StockagePizzaJPA();
	};
	
	
}
