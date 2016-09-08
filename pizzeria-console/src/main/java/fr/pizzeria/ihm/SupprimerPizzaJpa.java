package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;

public class SupprimerPizzaJpa extends Action{

	public SupprimerPizzaJpa( IhmHelper helper) {
		super("Supprimer Pizza (Jpa)", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("Vous voulez supprimez une pizza veuillez choisir laquelle (code)");
		Collection<Pizza> spj = this.helper.getStockagePizza().findAll();
		spj.stream().forEach(System.out::println);
		String code = helper.getScanner().next();
		helper.getStockagePizza().deleteTobject(code);

		System.out.println("Pizza supprimer avec succes \n");
	}
	
}
