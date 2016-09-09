package fr.pizzeria.ihm;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaJpa extends Action{

	public ModifierPizzaJpa(IhmHelper helper) {
		super("Modifier Pizza (Jpa)", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("Vous voulez modifier une pizza veuillez choisir laquelle");
		ListerPizzaAction lister = new ListerPizzaAction(helper);
		lister.execute();
		int id = helper.getScanner().nextInt();
		System.out.println("Ancien code");
		String ancienCode = helper.getScanner().next();
		System.out.println("Veuillez saisir le nouveau code");
		String code = helper.getScanner().next();
		System.out.println("Veuillez saisir le nouveau nom de la pizza");
		String nom = helper.getScanner().next();
		System.out.println("Veuillez saisir le nouveau prix de la pizza");
		double prix = helper.getScanner().nextDouble();
		int i = 0;
		CategoriePizza[]tabPizza = CategoriePizza.values();
		for (CategoriePizza cat : tabPizza) {
			System.out.println(i+" - "+cat.name());
			i++;
		}
		int idcat = helper.getScanner().nextInt();
		String categorie = tabPizza[idcat].name();
		Pizza nouvellePizza = new Pizza(id);
		nouvellePizza.setNom(nom);
		nouvellePizza.setPrix(prix);
		nouvellePizza.setCode(code);
		nouvellePizza.setCategorie(CategoriePizza.valueOf(categorie));
		//ancienCode est useless j'ai juste la flemme de tout recoder juste pour ça ^^
		helper.getStockagePizza().updateTobject(nouvellePizza, ancienCode);

		System.out.println("Pizza modifier avec succes \n");
	}
	
}
