package fr.pizzeria.service.ejb;

import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Pizza;


@Stateless
public class CommandeServizzaEJB {

	@PersistenceContext
	EntityManager em;
	
	
	//@Schedule(second="*", minute="2", hour="*")
	public void saveCommande() throws GeneralSecurityException{
		int compt = 0;
		Client client = new Client("Mr TEST", "Test", "test@test.fr", "test");
		Pizza maPizza = new Pizza("REI","Reine",18, CategoriePizza.VIANDE);
		Set<Pizza> pizzas = new HashSet<>();
		pizzas.add(maPizza);
		
		Commande commande = new Commande("test"+compt, Calendar.getInstance(), client, pizzas);
		
		em.persist(commande);
	}
}
