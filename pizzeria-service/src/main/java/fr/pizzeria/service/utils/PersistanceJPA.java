package fr.pizzeria.service.utils;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class PersistanceJPA {
	
	public static void maPersistance(Consumer<EntityManager> code, EntityManagerFactory emFactory) {
		EntityManager em = emFactory.createEntityManager();
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

}
