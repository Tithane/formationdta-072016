package fr.pizzeria.service;

import java.util.Collection;


public interface Stockage<T> {

	Collection<T> findAll();

	void saveTobject(T newTobject);

	void updateTobject(T editTobject, String code);

	void deleteTobject(String ancienCode);

	
}
