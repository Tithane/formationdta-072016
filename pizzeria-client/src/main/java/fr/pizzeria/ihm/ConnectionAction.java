package fr.pizzeria.ihm;

import fr.pizzeria.model.Client;

public class ConnectionAction extends Action {

	public ConnectionAction(IhmHelper helper) {
		super("Connection", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("En cours de Programmation ...");
		System.out.println("***** Connection *****");
		System.out.println("Veuillez entrer votre adresse mail");
		String mail = helper.getScanner().next();
		System.out.println("Veuillez saisir votre mot de passe");
		String password = helper.getScanner().next();
		Client testClient = new Client();
		testClient.setMail(mail);
		try {
			testClient.setPasswordEncrypt(password);
		} catch (Exception e) {
			System.out.println("Erreur : "+e.getMessage() );
		}
		
		Client monClient = helper.getStockageClient().getAuthentification(testClient);
		if (monClient != null) {
			MenuClientAuthentifie menu = new MenuClientAuthentifie(helper, monClient);
			menu.start();
		} else {
			System.out.println("Erreur Mot de passe et/ou Mail invalide ...");
		}
	}

}
