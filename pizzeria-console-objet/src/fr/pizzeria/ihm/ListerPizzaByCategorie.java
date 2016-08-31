package fr.pizzeria.ihm;

import java.util.Collection;
import java.util.stream.Collectors;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ListerPizzaByCategorie extends Action {

	public ListerPizzaByCategorie(IhmHelper helper) {
		super("Lister les pizzas(groupées par catégorie)", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("**** Liste de pizzas ****");
		Collection<Pizza> objectsT = this.helper.getStockagePizza().findAll();
		objectsT.stream().collect(Collectors.groupingBy(p -> p.getCategorie())).forEach((cle,valeur)->{ System.out.println(cle + " ====> " + valeur);});
	}
	

}
