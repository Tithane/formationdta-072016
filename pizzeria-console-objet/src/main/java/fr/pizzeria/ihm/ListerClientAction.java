package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Client;

public class ListerClientAction extends Action {

	public ListerClientAction(IhmHelper helper) {
		super("Lister les clients", helper);
	}

	public void execute() {
		System.out.println("**** Liste de Client ****");
		Collection<Client> clients = this.helper.getStockageClient().findAll();
		for (Client client : clients) {
			System.out.println(client);
		}
		System.out.println("\n");
	}
}
