package fr.pizzeria.ihm;

import fr.pizzeria.model.Client;

public class CommanderAction extends Action {

	public CommanderAction(IhmHelper helper, Client client) {
		super("Commander une pizza", helper, client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("***** Commande de Pizzas *****");
		
	}
	
	

}
