package fr.pizzeria.ihm;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Scanner;

import fr.pizzeria.exception.SaisieEntierException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockageClient;
import fr.pizzeria.service.StockagePizzaMap;

public class IhmHelper {

	private Stockage<Livreur> stockageLivreur;
	private Stockage<Pizza> stockagePizza;
	private Stockage<Client> stockageClient;
	private Scanner scanner;

	public IhmHelper(Stockage<Pizza> stockage, Stockage<Client> stockageC, Stockage<Livreur> stockageL,Scanner scanner) {
		super();
		this.stockageLivreur = stockageL;
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

	public Stockage<Pizza> getStockagePizza() {
		return stockagePizza;
	}

	
	//TEST
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		Stockage<Pizza> stockage = new StockagePizzaMap();
//		Stockage<Client> stockageC = new StockageClient();
//
//		IhmHelper helper = new IhmHelper(stockage, stockageC, scanner);
//
//		Stockage<Pizza> stockPizzzzz =helper.getStockage(Pizza.class);
//		
//		Collection<Pizza> listePizzas = stockPizzzzz.findAll();
//		
//		for (Pizza pizza : listePizzas) {
//			System.out.println(pizza.getNom());
//		}
//	}
	
	

	public <T> Stockage<T> getStockage(Class<T> entityClass) {
		for (Field field : IhmHelper.class.getDeclaredFields()) {

			// System.out.println(field);
			if (field.getType().isAssignableFrom(Stockage.class)) {
				Class<?> classe = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

				if (entityClass.equals(classe)) {
					try {
						return (Stockage<T>) field.get(this);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public Stockage<Client> getStockageClient() {
		return stockageClient;
	}
	public Stockage<Livreur> getStockageLivreur() {
		return stockageLivreur;
	}

}
