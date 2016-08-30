package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Pizza;

public class ListerPizzaAction extends Action {

	public ListerPizzaAction(IhmHelper helper) {
		super("Lister les pizzas", helper);
	}

	public void execute() {
		System.out.println("**** Liste de Pizzas ****");
		Collection<Pizza> pizzas = this.helper.getStockagePizza().findAll();
		for (Pizza pizzaEnCours : pizzas) {
			System.out.println(pizzaEnCours.getCode() + " " + pizzaEnCours.getNom() + " " + pizzaEnCours.getPrix());
		}
		System.out.println("\n");
	}

}
