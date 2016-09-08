package fr.pizzeria.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;

public class StockagePizzaJPA implements Stockage<Pizza> {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("pizzeria-unit");

	Collection<Pizza> pizzas = new ArrayList<>();

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

}
