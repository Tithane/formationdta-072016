package fr.pizzeria.ihm;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaJpa extends Action {

	public AjouterPizzaJpa(IhmHelper helper) {
		super("Ajouter Pizza (Jpa)", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("Vous voulez ajoutez une pizza");
		System.out.println("Veuillez saisir le code");
		String code = helper.getScanner().next();
		System.out.println("Veuillez saisir le nom de la pizza");
		String nom = helper.getScanner().next();
		System.out.println("Veuillez saisir le prix de la pizza");
		double prix = helper.getScanner().nextDouble();
		
		int i = 0;
		CategoriePizza[]tabPizza = CategoriePizza.values();
		for (CategoriePizza cat : tabPizza) {
			System.out.println(i+" - "+cat.name());
			i++;
		}
		int idcat = helper.getScanner().nextInt();
		String categorie = tabPizza[idcat].name();
		
		// creation de la nouvelle pizza
		
		Pizza nouvellePizza = new Pizza(code, nom, prix, CategoriePizza.valueOf(categorie));
		helper.getStockagePizza().saveTobject(nouvellePizza);

		System.out.println("Pizza ajouter avec succes" + "\n");

	}
	 
}
