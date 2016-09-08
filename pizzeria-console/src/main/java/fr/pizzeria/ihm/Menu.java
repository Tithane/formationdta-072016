package fr.pizzeria.ihm;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.reflections.Reflections;

import fr.pizzeria.exception.SaisieEntierException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;

public class Menu {

	private static final int CHOIX_SORTIR = 99;
	private Map<Integer, Action> actions = new HashMap<>();
	private IhmHelper ihmHelper;

	public Menu(IhmHelper helper) {
		this.actions.put(1, new ListerPizzaActionJpa(helper));
		this.actions.put(2, new AjouterPizzaJpa(helper));
		this.actions.put(3, new ModifierPizzaJpa(helper));
		this.actions.put(4, new SupprimerPizzaJpa(helper));
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
		this.actions.put(17, new ImportPizza(helper));
		
		
		//************Reflection*************/
//		Reflections reflections = new Reflections();
//		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ActionAnnotation.class);
//		AtomicInteger yournotkillme = new AtomicInteger(0);
//		annotated.stream().forEach(t -> {
//			try{
//				this.actions.put(yournotkillme.incrementAndGet(), (Action) t.getConstructor(IhmHelper.class).newInstance(helper));
//				}catch(Exception e){
//					
//				}
//		});

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
		Calendar instance = Calendar.getInstance();
		DateFormatUtils.format(instance, "dd/MM "+"-"+" hh:mm");
		System.out.println(DateFormatUtils.format(instance, "dd/MM - hh:mm"));
		
		
		
		actions.forEach((numero, valeur)->{
			Action actionEnCours = valeur;
			String libelleAction = actionEnCours.getLibelle();
			System.out.println(numero+" "+libelleAction);
		});
		

		
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
