package fr.pizzeria.service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


import fr.pizzeria.exception.SaisieCodeException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;


public class StockagePizzaMap implements Stockage<Pizza> {

	public Map<String, Pizza> pizzas = new TreeMap<>();

	public StockagePizzaMap() {
		this.pizzas.put("PEP", new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		this.pizzas.put("MAR", new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		this.pizzas.put("REI", new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		this.pizzas.put("FRO", new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		this.pizzas.put("CAN", new Pizza("CAN", "La cannibale", 12.50 , CategoriePizza.VIANDE));
		this.pizzas.put("SAV", new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.POISSON));
		this.pizzas.put("ORI", new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.SANS_VIANDE));
		this.pizzas.put("IND", new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));

	}

	@Override
	public Collection<Pizza> findAll() {
		return pizzas.values();
	}

	@Override
	public void saveTobject(Pizza newPizza) {
		this.pizzas.put(newPizza.getCode(), newPizza);
	}

	public void saisirCode(Pizza newPizza) throws SaisieCodeException {
		if (newPizza.getCode().length() < 3 || newPizza.getCode().length() > 3) {
			SaisieCodeException CodeException = new SaisieCodeException("Attention rentrer un code a 3 chiffres");
			throw CodeException;
		}
	}

	@Override
	public void updateTobject(Pizza editPizza, String ancienCode) {
		this.pizzas.remove(ancienCode);
		this.pizzas.put(editPizza.getCode(), editPizza);

	}

	@Override
	public void deleteTobject(String ancienCode) {
		this.pizzas.remove(ancienCode);
	}



}
