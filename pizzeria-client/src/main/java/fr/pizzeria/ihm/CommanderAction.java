package fr.pizzeria.ihm;

import java.util.Collection;


import fr.pizzeria.model.Client;
import fr.pizzeria.model.Pizza;

public class CommanderAction extends Action {
	String FINDECOMMANDE = "!";
	Pizza maPizza;

	public CommanderAction(IhmHelper helper, Client client) {
		super("Commander une pizza", helper, client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("***** Commande de Pizzas *****");
		
		Collection<Pizza> listePizza = helper.getStockagePizza().findAll();
		listePizza.stream().forEach(System.out::println);
		String saisie = "0";
		while(saisie.equals(FINDECOMMANDE)){
			System.out.println("Sélectionnez le numéro de la pizza desiré et appuyer sur\"!\" pour terminer ... ");
			saisie = helper.getScanner().next();
			Pizza pizzaTest = new Pizza();
			try {
				pizzaTest.setId(Integer.parseInt(saisie));
				maPizza = helper.getStockagePizza().getPizza(pizzaTest);
			} catch (Exception e) {
				
			}
			
			
		}
		
	}
	
	

}
