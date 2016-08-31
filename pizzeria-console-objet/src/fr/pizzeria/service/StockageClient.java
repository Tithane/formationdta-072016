package fr.pizzeria.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;
import fr.pizzeria.model.Client;

public class StockageClient implements Stockage<Client> {

	public List<Client> clients = new ArrayList<>();

	public StockageClient() {
		// Import code here if file exist
		
		// FIn de test

		this.clients.add(new Client("moustafa", "ababa"));
		this.clients.add(new Client("pizza", "i like it"));
		this.clients.add(new Client("ti", "banjo"));
		this.clients.add(new Client("arthur", "blablabla"));

	}

	@Override
	public Collection<Client> findAll() {
		return clients;
	}

	@Override
	public void saveTobject(Client newTobject) {
		// TODO Auto-generated method stub
		this.clients.add(newTobject);

	}

	@Override
	public void updateTobject(Client editTobject, String code) {
		try {
			Double montant = Double.parseDouble(code);
			Client monClient = clients.get(editTobject.getId());
			if (montant < 0) {
				monClient.debiterCompte(montant);
			} else if (montant > 0) {
				monClient.crediterCompte(montant);
			} else
				System.out.println(montant.toString());
			System.out.println("Op�ration effectu�e avec succes\n");
		} catch (CreditException ce) {
			System.out.println("Erreur : Solde du compte trop important !\n");
		} catch (DebitException de) {
			System.out.println("Erreur : Solde du compte insuffisant !\n");
		} catch (Exception e) {
			System.out.println("Ce client n'existe pas\n");
		}

	}

	@Override
	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub

	}

}
