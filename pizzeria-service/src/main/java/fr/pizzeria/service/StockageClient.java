package fr.pizzeria.service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;
import fr.pizzeria.model.Client;

public class StockageClient implements Stockage<Client> {

	public List<Client> clients = new ArrayList<Client>();

	public StockageClient() {
		
		this.clients.add(new Client("moustafa", "ababa"));
		this.clients.add(new Client("pizza", "i like it"));
		this.clients.add(new Client("ti", "banjo"));
		this.clients.add(new Client("arthur", "blablabla"));

	}
	public List<Client>getDataXML(URI file){
		try {
			Files.readAllLines(Paths.get(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public Collection<Client> findAll() {
		return clients;
	}

	public void saveTobject(Client newTobject) {
		// TODO Auto-generated method stub
		this.clients.add(newTobject);

	}

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

	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub

	}

}
