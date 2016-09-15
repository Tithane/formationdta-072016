package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.StockagePizzaJPA;

public class ListerPizzaController extends HttpServlet {
	Collection<Pizza> liste_pizza = new ArrayList<>();
	StockagePizzaJPA monStockagePizza = new StockagePizzaJPA();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/listerPizzas.jsp");
		liste_pizza = monStockagePizza.findAll();
		req.setAttribute("liste-pizzas", liste_pizza);
		rd.forward(req, resp);
	}
}
