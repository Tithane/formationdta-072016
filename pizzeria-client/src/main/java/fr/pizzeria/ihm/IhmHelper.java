package fr.pizzeria.ihm;


import java.util.Scanner;

import fr.pizzeria.exception.SaisieEntierException;
import fr.pizzeria.service.StockageClientJPA;
import fr.pizzeria.service.StockagePizzaJPA;

public class IhmHelper {
	private StockagePizzaJPA stockagePizza;
	private StockageClientJPA stockageClient;
	private Scanner scanner;

	public IhmHelper(
			StockagePizzaJPA stockage,
			StockageClientJPA stockageC,Scanner scanner) {
		super();
		this.stockageClient = stockageC;
		this.stockagePizza = stockage;
		this.scanner = scanner;
	}

	public int saisirEntier() throws SaisieEntierException {
		try {
			String saisie = scanner.next();
			return Integer.parseInt(saisie);
		} catch (NumberFormatException e) {
			throw new SaisieEntierException(e);
		}
	}

	public StockagePizzaJPA getStockagePizza() {
		return stockagePizza;
	}
	public StockageClientJPA getStockageClient() {
		return stockageClient;
	}
	

	public Scanner getScanner() {
		return scanner;
	}



}
