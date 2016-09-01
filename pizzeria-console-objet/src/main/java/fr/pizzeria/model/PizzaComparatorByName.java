package fr.pizzeria.model;

import java.util.Comparator;

public class PizzaComparatorByName implements Comparator<Pizza> {

	@Override
	public int compare(Pizza o1, Pizza o2) {
		int result = o1.getNom().compareTo(o2.getNom());
		return result;
	}

}
