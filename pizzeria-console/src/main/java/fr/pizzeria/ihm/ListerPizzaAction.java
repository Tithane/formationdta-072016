package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Pizza;

public class ListerPizzaAction extends Action {

	
	private Collection<Pizza> objetT=this.helper.getStockagePizza().findAll();

	public Collection<Pizza> getListe() {
		if (objetT != null) {
			objetT.clear();
			objetT = this.helper.getStockagePizza().findAll();
		}
		return objetT;
	}
	
	public ListerPizzaAction(IhmHelper helper) {
		super("Lister les pizzas", helper);
	}

	public void execute() {
		System.out.println("**** Liste de pizzas ****");

//		Collection<Pizza> objectsT = this.helper.getStockagePizza().findAll();
//		objectsT.stream().forEach(System.out::println);
		getListe().stream().forEach(System.out::println);
	}

}
