package fr.pizzeria.model;

import java.util.Comparator;

public class PizzaComparatorById implements Comparator<Pizza> {

	@Override
	public int compare(Pizza o1, Pizza o2) {
		int result = String.valueOf(o1.getId()).compareTo(String.valueOf(o2.getId()));
		return result;
	}

}
