package fr.pizzeria.model;

import java.util.Comparator;

public class PizzaComparatorByPrice implements Comparator<Pizza> {

	@Override
	public int compare(Pizza o1, Pizza o2) {
		int result = String.valueOf(o1.getPrix()).compareTo(String.valueOf(o2.getPrix()));
		return result;
	}
}
