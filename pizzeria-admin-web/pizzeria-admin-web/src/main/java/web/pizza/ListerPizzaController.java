package web.pizza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaJPA;
import fr.pizzeria.service.ejb.PizzaServiceEJB;
import fr.pizzeria.service.utils.PersistanceUtils;


@WebServlet("/pizzas/list")
public class ListerPizzaController extends HttpServlet {
	
	//@Inject @Named("stockagePizzaJPA")private Stockage<Pizza> stockagePizza;
	@EJB private PizzaServiceEJB stockagePizza ;
	Collection<Pizza> liste_pizza = new ArrayList<>();
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/listerPizzas.jsp");
		liste_pizza = stockagePizza.findAll();
		CategoriePizza[] catPizza = CategoriePizza.values();
		//Integer nbSession = (Integer)req.getSession().getServletContext().getAttribute("compteur");
		req.setAttribute("listePizzas", liste_pizza);
		req.setAttribute("catPizza", catPizza);
		rd.forward(req, resp);
	}
}
