package web.pizza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.utils.PersistanceUtils;

public class ListerPizzaController extends HttpServlet {
	Collection<Pizza> liste_pizza = new ArrayList<>();
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/listerPizzas.jsp");
		liste_pizza = PersistanceUtils.getInstance().getStockagePizza().findAll();
		CategoriePizza[] catPizza = CategoriePizza.values();
		//Integer nbSession = (Integer)req.getSession().getServletContext().getAttribute("compteur");
		req.setAttribute("listePizzas", liste_pizza);
		req.setAttribute("catPizza", catPizza);
		rd.forward(req, resp);
	}
}
