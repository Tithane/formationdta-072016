package fr.pizzeria.service.ejb;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.ejb.event.NewCommandeEvent;



@Stateless
public class CommandeServiceEJB{

	@PersistenceContext
	EntityManager em;
	
	@Inject Event<NewCommandeEvent> monEvent;
	
	@Schedule(minute="*/2", hour="*")
	public void saveCommande() throws GeneralSecurityException{
		Client client = em.getReference(Client.class, 1);
		Set<Pizza> pizzas = new HashSet<>();
		TypedQuery<Pizza> createQuery = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
		pizzas.addAll(createQuery.getResultList());
		Commande commande = new Commande("COM"+UUID.randomUUID().toString(), Calendar.getInstance(), client, pizzas);
		
		em.persist(commande);
		monEvent.fire(new NewCommandeEvent(commande));
	}


	public Collection<Commande> findAll() {
		Set<Commande> listCommand = new HashSet<>();
		TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c JOIN FETCH c.pizzas", Commande.class);
		listCommand.addAll(query.getResultList());
		return listCommand ;
	}


	public void saveTobject(Commande newTobject) {
		// TODO Auto-generated method stub
		
	}


	public void updateTobject(Commande editTobject, String code) {
		// TODO Auto-generated method stub
		
	}


	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub
		
	}
	
	
}
