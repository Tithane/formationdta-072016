package fr.pizzeria.ihm;

import java.util.Collection;

import fr.pizzeria.model.Livreur;

public class ListerLivreurAction extends Action {

	public ListerLivreurAction(IhmHelper helper) {
		super("Lister les livreurs", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("**** Liste de Livreur ****");
		Collection<Livreur> Livreurs = this.helper.getStockageLivreur().findAll();
		for (Livreur Livreur : Livreurs) {
			System.out.println(Livreur);
		}
		System.out.println("\n");
	}
	
}
