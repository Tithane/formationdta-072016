package fr.pizzeria.service.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;

@Stateless
public class PizzaServiceEJB {

	@PersistenceContext
	EntityManager em;
	List<Pizza> pizzas = new ArrayList<>();

	public Collection<Pizza> findAll() {
		pizzas.clear();
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
		pizzas.addAll(query.getResultList());
		return pizzas;
	}

	public void saveTobject(Pizza newTobject) {
		List<Pizza> listePizza = em.createQuery("SELECT p FROM Pizza p WHERE code=:code", Pizza.class)
				.setParameter("code", newTobject.getCode()).getResultList();
		if (listePizza.isEmpty()) {
			em.persist(newTobject);
		}
	}

	public void updateTobject(Pizza editTobject, String code) {
		Pizza pizzaUpdate = em.getReference(Pizza.class, editTobject.getId());
		pizzaUpdate.setCode(editTobject.getCode());
		pizzaUpdate.setNom(editTobject.getNom());
		pizzaUpdate.setPrix(editTobject.getPrix());
		if (editTobject.getUrl() != null) {
			pizzaUpdate.setUrl(editTobject.getUrl());
		}
		pizzaUpdate.setCategorie(editTobject.getCategorie());
		em.merge(pizzaUpdate);

	}

	public void deleteTobject(String ancienCode) {
		Pizza pizza = em.createQuery("SELECT p FROM Pizza p WHERE code=:code", Pizza.class)
				.setParameter("code", ancienCode).getResultList().get(0);
		em.remove(pizza);

	}

}
