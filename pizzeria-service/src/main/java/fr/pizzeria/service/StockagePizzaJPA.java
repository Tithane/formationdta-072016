package fr.pizzeria.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;


@Named
public class StockagePizzaJPA implements Stockage<Pizza> {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("pizzeria-unit");

	Collection<Pizza> pizzas = new ArrayList<>();
	Pizza maPizza = null;

	public EntityManager createEm() {
		return emfactory.createEntityManager();
	}

	public void mutualiser(Consumer<EntityManager> code) {
		EntityManager em = createEm();
		em.getTransaction().begin();
		try {
			code.accept(em);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			e.getStackTrace();
		} finally {
			em.close();
		}
	}

	
	@Override
	public Collection<Pizza> findAll() {
		mutualiser(em -> {
			pizzas.clear();
			TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
			pizzas.addAll(query.getResultList());
		});
		return pizzas;
	}

	@Override
	public void saveTobject(Pizza newTobject) {
		mutualiser(em -> {
			List<Pizza> listePizza = em.createQuery("SELECT p FROM Pizza p WHERE code=:code", Pizza.class)
					.setParameter("code", newTobject.getCode()).getResultList();
			if (listePizza.isEmpty()) {
				em.persist(newTobject);
			}
		});
	}

	@Override
	public void updateTobject(Pizza editTobject, String code) {
		mutualiser(em -> {
			Pizza pizzaUpdate = em.getReference(Pizza.class, editTobject.getId());
			pizzaUpdate.setCode(editTobject.getCode());
			pizzaUpdate.setNom(editTobject.getNom());
			pizzaUpdate.setPrix(editTobject.getPrix());
			if(editTobject.getUrl()!=null){
				pizzaUpdate.setUrl(editTobject.getUrl());
			}
			pizzaUpdate.setCategorie(editTobject.getCategorie());
			em.merge(pizzaUpdate);
		});
		

	}

	@Override
	public void deleteTobject(String ancienCode) {
		mutualiser(em -> {
			Pizza pizza = em.createQuery("SELECT p FROM Pizza p WHERE code=:code", Pizza.class)
					.setParameter("code", ancienCode).getResultList().get(0);
			em.remove(pizza);
		});
	}
	
	public Pizza getPizza(Pizza pizza){
		EntityManager em = createEm();
		try {
			Pizza pizzaUpdate = em.getReference(Pizza.class, pizza.getId());
			maPizza = pizzaUpdate ;
		} catch (Exception e) {
			throw e;
		}
		return maPizza;
	}
	public Pizza getPizzaByCode(Pizza pizza){
		mutualiser(em -> {
			maPizza = em.createQuery("SELECT p FROM Pizza p WHERE code=:code", Pizza.class)
					.setParameter("code", pizza.getCode()).getResultList().get(0);
		});
		return maPizza;
	}

}
