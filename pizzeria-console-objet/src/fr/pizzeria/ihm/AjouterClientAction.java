package fr.pizzeria.ihm;

import fr.pizzeria.model.Client;

public class AjouterClientAction extends Action {

	public AjouterClientAction(IhmHelper helper) {
		super("Ajouter un client", helper);
	}

	@Override
	public void execute() {

		// récuperation de la saisie
		System.out.println("Vous voulez ajoutez un client");
		System.out.println("Veuillez saisir son nom");
		String nom = helper.getScanner().next();
		System.out.println("Veuillez saisir son prénom");
		String prenom = helper.getScanner().next();
		// creation du nouveau Client
		Client nouveauClient = new Client(nom, prenom);
		helper.getStockageClient().saveTobject(nouveauClient);

		System.out.println("Client ajouter avec succes" + "\n");

	}
}
