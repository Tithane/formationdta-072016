package fr.pizzeria.console;

import java.util.Scanner;
import java.util.logging.Level;

import fr.pizzeria.ihm.IhmHelper;
import fr.pizzeria.ihm.Menu;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.JDBCPizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockageClient;
import fr.pizzeria.service.StockageLivreur;
import fr.pizzeria.service.StockagePizzaJPA;
import fr.pizzeria.service.StockagePizzaMap;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {

		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Scanner scanner = new Scanner(System.in);

		//JDBCPizza stockage = new JDBCPizza();
		//Stockage<Pizza> stockage = new StockagePizzaMap();
		StockagePizzaJPA stockage = new StockagePizzaJPA();
		Stockage<Client> stockageC = new StockageClient();
		Stockage<Livreur> stockageL = new StockageLivreur();

		IhmHelper helper = new IhmHelper(stockage,stockageC,stockageL, scanner);

		// Afficher le Menu

		Menu listMenu = new Menu(helper);

		listMenu.start();

		scanner.close();

	}

}
