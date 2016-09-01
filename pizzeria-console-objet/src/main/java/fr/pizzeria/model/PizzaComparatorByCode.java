package fr.pizzeria.model;

import java.util.Comparator;

public class PizzaComparatorByCode implements Comparator<Pizza> {

	@Override
	public int compare(Pizza o1, Pizza o2) {
		int result = o1.getCode().compareTo(o2.getCode());
		return result;
	}

}
