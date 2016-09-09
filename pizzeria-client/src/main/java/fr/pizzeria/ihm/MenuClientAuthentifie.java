package fr.pizzeria.ihm;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;

import fr.pizzeria.exception.SaisieEntierException;
import fr.pizzeria.model.Client;

public class MenuClientAuthentifie {

	private static final int CHOIX_SORTIR = 99;
	private Map<Integer, Action> actions = new HashMap<>();
	private IhmHelper ihmHelper;
	private final Client client;

	public MenuClientAuthentifie(IhmHelper helper, Client clientActif) {
		this.actions.put(1, new CommanderAction(helper,clientActif));
		this.actions.put(2, new ListerCommandesAction(helper,clientActif));
		this.client = clientActif;
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
		System.out.println("***** Menu Client *****");
		Calendar instance = Calendar.getInstance();
		System.out.println(DateFormatUtils.format(instance, "dd/MM - hh:mm"));
		System.out.println("Bienvenue "+client.getNom()+" "+client.getPrenom());
		
		actions.forEach((numero, valeur)->{
			Action actionEnCours = valeur;
			String libelleAction = actionEnCours.getLibelle();
			System.out.println(numero+" "+libelleAction);
		});
		System.out.println(CHOIX_SORTIR + ". Deconnexion" + "\n");
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
