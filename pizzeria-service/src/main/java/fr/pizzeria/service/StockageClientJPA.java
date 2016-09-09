package fr.pizzeria.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.utils.PersistanceJPA;

public class StockageClientJPA implements Stockage<Client>{
	
	Collection<Client> clients = new ArrayList<>();
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("pizzeria-unit");
	Client monClient = null;

	@Override
	public Collection<Client> findAll() {
		PersistanceJPA.maPersistance(em ->{
			clients.clear();
			TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
			clients.addAll(query.getResultList());
		}, emfactory);
		return clients;
	}

	@Override
	public void saveTobject(Client newTobject) {
		PersistanceJPA.maPersistance(em->{
			List<Client> listeClient = em.createQuery("SELECT c FROM Client c WHERE mail=:mail", Client.class)
					.setParameter("mail", newTobject.getMail()).getResultList();
			if (listeClient.isEmpty()) {
				em.persist(newTobject);
			}
		}, emfactory);
		
	}

	@Override
	public void updateTobject(Client editTobject, String code) {
		
	}

	@Override
	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub
		
	}
	
	public Client getAuthentification(Client client){
		PersistanceJPA.maPersistance(em->{
			List<Client> listeClient = em.createQuery("SELECT c FROM Client c WHERE mail=:mail AND password =:password", Client.class)
					.setParameter("mail", client.getMail())
					.setParameter("password", client.getPassword())
					.getResultList();
			if (!listeClient.isEmpty()) {
				monClient = listeClient.get(0);
			}
		}, emfactory);
		
		return monClient;
	}
	

}
