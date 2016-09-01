package fr.pizzeria.ihm;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import fr.pizzeria.model.Pizza;

public class PizzaChere extends Action {

	public PizzaChere(IhmHelper helper) {
		super("Pizza la plus chère", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		Collection<Pizza> objectsT = this.helper.getStockagePizza().findAll();
		Pizza pizza  = objectsT.stream().collect(Collectors.maxBy(Comparator.comparing(Pizza::getPrix))).get();
		System.out.println(pizza);
	}
	
	

}
