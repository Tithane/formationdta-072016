package fr.pizzeria.service.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.AbstractPersonne;


@Stateless
public class PersonneServiceEJB{
	List<AbstractPersonne> listPersonne = new ArrayList<>();
	@PersistenceContext
	EntityManager em;

	public Collection<AbstractPersonne> findAll() {
		listPersonne.clear();
		TypedQuery<AbstractPersonne> query = em.createQuery("SELECT p FROM Abstractpersonne p", AbstractPersonne.class);
		listPersonne.addAll(query.getResultList());
		return listPersonne;
	}

	public void saveTobject(AbstractPersonne newTobject) {
		em.persist(newTobject);
	}

	public void updateTobject(AbstractPersonne editTobject, String code) {
		// TODO Auto-generated method stub
		
	}

	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub
		
	}



}
