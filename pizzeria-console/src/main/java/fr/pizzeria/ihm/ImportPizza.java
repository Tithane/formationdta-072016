package fr.pizzeria.ihm;

import java.sql.SQLException;

import fr.pizzeria.service.InsertionPizza;

public class ImportPizza extends Action {

	public ImportPizza(IhmHelper helper) {
		super("Importation pizza", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		InsertionPizza newPizza = new InsertionPizza();
		try {
			newPizza.saveTobject(newPizza.findAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur : "+e.getMessage());
		}
	}
	
	
}
