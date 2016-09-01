package fr.pizzeria.ihm;

import fr.pizzeria.model.Client;
@ActionAnnotation
public class DebiterClientAction extends Action {

	public DebiterClientAction(IhmHelper helper) {
		super("Debiter un client", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		ListerClientAction lister_les_clients = new ListerClientAction(helper);
		lister_les_clients.execute();
		System.out.println("Selectionner l'id du client � dediter :");
		int saisie =  helper.getScanner().nextInt();
		Client client = new Client(saisie);
		System.out.println("Selectionner le montant :");
		Double montant = helper.getScanner().nextDouble();
		helper.getStockageClient().updateTobject(client, "-"+montant.toString());
	}
}
