package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.exception.SaisieEntierException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;

public class Menu {

	private static final int CHOIX_SORTIR = 99;
	private Map<Integer, Action> actions = new HashMap<>();
	private IhmHelper ihmHelper;

	public Menu(IhmHelper helper) {
		this.actions.put(1, new Lister<Pizza>(Pizza.class,helper));
		this.actions.put(2, new AjouterPizzaAction(helper));
		this.actions.put(3, new ModifierPizzaAction(helper));
		this.actions.put(4, new SupprimerPizzaAction(helper));
		this.actions.put(5, new Lister<Client>(Client.class,helper));
		this.actions.put(6, new AjouterClientAction(helper));
		this.actions.put(7, new CrediterClientAction(helper));
		this.actions.put(8, new DebiterClientAction(helper));
		this.actions.put(9, new Lister<Livreur>(Livreur.class,helper));
		this.actions.put(10, new Statistique(helper));
		this.actions.put(11, new VirementAction(helper));
		this.actions.put(12, new ListerPizzaByCategorie(helper));
		this.actions.put(13, new PizzaChere(helper));
		this.actions.put(14, new ExportAction<Pizza>(Pizza.class, helper));
		this.actions.put(15, new ExportAction<Client>(Client.class, helper));
		this.actions.put(16, new ExportAction<Livreur>(Livreur.class, helper));

		this.ihmHelper = helper;
	}

	public void start() {
		boolean result = false;
		do {
			affichageM();
			result = choisir();
		} while (!result);
	}

	public void affichageM() {
		System.out.println("***** Pizzeria Administration *****");
		
		actions.forEach((numero, valeur)->{
			Action actionEnCours = valeur;
			String libelleAction = actionEnCours.getLibelle();
			System.out.println(numero+" "+libelleAction);
		});
		
//		for (Integer numero : actions.keySet()) {
//			Action ActionEnCours = actions.get(numero);
//			String libelleAction = ActionEnCours.getLibelle();
//			System.out.println(numero + " " + libelleAction);
//
//		}
		System.out.println(CHOIX_SORTIR + ". Quitter" + "\n");
	}

	public boolean choisir() {
		System.out.println("Veuillez choisir une option");
		int choix = 0;
		try {
			// Instructions susceptibles de provoquer des erreurs;

			choix = ihmHelper.saisirEntier();
			if (!actions.containsKey(choix)) {
				if (choix != CHOIX_SORTIR) {
					System.out.println("Erreur de saisie, veuillez recommencer!" + "\n");
				}
			} else {

				Action LaBonneAction = actions.get(choix);
				LaBonneAction.execute();

			}
		} catch (SaisieEntierException e) {
			System.out.println(e.getMessage());
		}

		return choix == CHOIX_SORTIR;
	}

}
