package fr.pizzeria.ihm;

import java.security.GeneralSecurityException;

import fr.pizzeria.model.Client;

public class InscriptionAction extends Action {

	public InscriptionAction(IhmHelper helper) {
		super("S'inscrire", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("***** Inscription d'un Client *****");
		System.out.println("Veuillez saisir votre nom");
		String nom = helper.getScanner().next();
		System.out.println("Veuillez saisir votre prénom");
		String prenom = helper.getScanner().next();
		System.out.println("Veuillez saisir votre mail");
		String mail = helper.getScanner().next();
		System.out.println("Veuillez saisir votre mot de passe");
		String mdp = helper.getScanner().next();
		
		Client nouveauClient;
		try {
			nouveauClient = new Client(nom, prenom, mail, mdp);
			System.out.println(nouveauClient);
			helper.getStockageClient().saveTobject(nouveauClient);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur : "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	

}
