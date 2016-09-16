package web.pizza;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.StockagePizzaJPA;
import fr.pizzeria.service.utils.PersistanceUtils;

public class DeletePizzaController extends HttpServlet {
	Collection<Pizza> liste_pizza = new ArrayList<>();
	
	
	public void MesPizzas(){
		liste_pizza = PersistanceUtils.getInstance().getStockagePizza().findAll();
	}
	
	public boolean TestExist(Pizza pizza){
		MesPizzas();
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
			PersistanceUtils.getInstance().getStockagePizza().deleteTobject(maPizza.getCode());
			resp.sendRedirect(req.getContextPath()+"/pizzas/list");
		} else {
			
		}
	}

}
