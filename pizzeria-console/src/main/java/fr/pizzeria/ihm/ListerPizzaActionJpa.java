package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaJPA;

public class ListerPizzaActionJpa extends Action {

	public ListerPizzaActionJpa(IhmHelper helper) {
		super("Lister Pizza (JPA)", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Stockage<Pizza> spj = this.helper.getStockagePizza();
		spj.findAll().stream().forEach(System.out::println);
	}

	
}
