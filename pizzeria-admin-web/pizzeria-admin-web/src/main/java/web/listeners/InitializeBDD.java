package web.listeners;

import java.security.GeneralSecurityException;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.ejb.PizzaServiceEJB;


@WebListener
public class InitializeBDD implements ServletContextListener{
	@EJB private PizzaServiceEJB stockagePizza;
	
	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		Pizza maPizza = new Pizza("REI","Reine",18, CategoriePizza.VIANDE);
		Pizza maPizza2 =  new Pizza("FDM","Fruit_de_mer",22, CategoriePizza.SANS_VIANDE);
		try {
			Client client = new Client("Mr TEST", "Test", "test@test.fr", "test");
			
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stockagePizza.saveTobject(maPizza);
		stockagePizza.saveTobject(maPizza2);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
