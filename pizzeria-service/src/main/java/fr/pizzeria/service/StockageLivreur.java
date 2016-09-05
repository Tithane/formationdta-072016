package fr.pizzeria.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Livreur;

public class StockageLivreur implements Stockage<Livreur> {

	public List<Livreur> livreurs = new ArrayList<>();

	public StockageLivreur() {
		this.livreurs.add(new Livreur("le", "transporteur"));
		this.livreurs.add(new Livreur("speedy", "gonzaless"));
	}

	@Override
	public Collection<Livreur> findAll() {
		// TODO Auto-generated method stub
		return livreurs;
	}

	@Override
	public void saveTobject(Livreur newTobject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTobject(Livreur editTobject, String code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub
		
	}
	

}
