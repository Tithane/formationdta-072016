package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.model.Client;

public class CrediterClientAction extends Action {

	public CrediterClientAction(IhmHelper helper) {
		super("Crediter un client", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		ListerClientAction lister_les_clients = new ListerClientAction(helper);
		lister_les_clients.execute();
		System.out.println("Selectionner l'id du client à crediter :");
		int saisie =  helper.getScanner().nextInt();
		Client client = new Client(saisie);
		System.out.println("Selectionner le montant :");
		double montant = helper.getScanner().nextDouble();
		helper.getStockageClient().updateTobject(client, String.valueOf(montant));
	}

		
}
