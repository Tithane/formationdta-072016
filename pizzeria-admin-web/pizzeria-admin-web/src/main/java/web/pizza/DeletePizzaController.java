package web.pizza;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaJPA;
import fr.pizzeria.service.ejb.PizzaServiceEJB;
import fr.pizzeria.service.utils.PersistanceUtils;


@WebServlet("/pizzas/delete")
public class DeletePizzaController extends HttpServlet {
	Collection<Pizza> liste_pizza = new ArrayList<>();
	
	//@Inject @Named("stockagePizzaJPA")private Stockage<Pizza> stockagePizza;
	@EJB private PizzaServiceEJB stockagePizza ;
	
	
	public boolean TestExist(Pizza pizza){
		liste_pizza = stockagePizza.findAll();
		AtomicBoolean etat = new AtomicBoolean() ;
		etat.set(false);
		liste_pizza.stream().forEach(pizzaBase->{
			if(pizzaBase.getCode().equals(pizza.getCode())){etat.set(true);};
		});
		return etat.get();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		Pizza maPizza = new Pizza(code);
		if (TestExist(maPizza)) {
			stockagePizza.deleteTobject(maPizza.getCode());
			resp.sendRedirect(req.getContextPath()+"/pizzas/list");
		} else {
			
		}
	}

}
