package fr.pizzeria.ihm;

import fr.pizzeria.model.Client;

public class ListerCommandesAction extends Action {

	public ListerCommandesAction(IhmHelper helper, Client client) {
		super("Lister les commandes", helper,client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("En construction ...");
	}
	
}
