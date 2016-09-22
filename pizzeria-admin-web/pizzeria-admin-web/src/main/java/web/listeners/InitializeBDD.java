package web.listeners;

import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;

import fr.pizzeria.model.AbstractPersonne;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.ejb.PersonneServiceEJB;
import fr.pizzeria.service.ejb.PizzaServiceEJB;


@WebListener
public class InitializeBDD implements ServletContextListener{
	@EJB private PizzaServiceEJB stockagePizza;
	@EJB private PersonneServiceEJB stockagePersonne;
	
	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Demarrage de l'appli");
		Pizza maPizza = new Pizza("REI","Reine",18, CategoriePizza.VIANDE);
		Pizza maPizza2 =  new Pizza("FDM","Fruit_de_mer",22, CategoriePizza.SANS_VIANDE);
		try {
			AbstractPersonne client = new Client("Mr TEST", "Test", "test@test.fr", "test");
			stockagePersonne.saveTobject(client);
			
			
		} catch (GeneralSecurityException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Erreur lors de la creation du client",e);
		}
		
		stockagePizza.saveTobject(maPizza);
		stockagePizza.saveTobject(maPizza2);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
