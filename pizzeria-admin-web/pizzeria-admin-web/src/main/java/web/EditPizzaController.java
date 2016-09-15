package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.StockagePizzaJPA;

public class EditPizzaController extends HttpServlet {
	Collection<Pizza> liste_pizza = new ArrayList<>();
	StockagePizzaJPA monStockagePizza = new StockagePizzaJPA();
	Pizza pizza= null;
	
	
	
	public boolean TestExist(Pizza pizza){
		liste_pizza = monStockagePizza.findAll();
		AtomicBoolean etat = new AtomicBoolean() ;
		etat.set(false);
		liste_pizza.stream().forEach(pizzaBase->{
			if(pizzaBase.getCode().equals(pizza.getCode())){etat.set(true);};
		});
		return etat.get();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/editPizza.jsp");
		if(req.getParameter("code")!= null){
				pizza = monStockagePizza.getPizzaByCode(new Pizza((String)req.getParameter("code")));
				req.setAttribute("maPizza", pizza);
			}else {
				req.setAttribute("maPizza", null);
			}
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Pizza maPizza = new Pizza();
		if(req.getParameter("id")!=null){
			Integer id = Integer.parseInt(req.getParameter("id"));
			maPizza.setId(id);
		}
		String url = "noImage.jpg";
		if(!req.getParameter("url").isEmpty()){
			url = req.getParameter("url");
		}
		maPizza.setUrl(url);
		String code =  req.getParameter("code");
		Double prix = Double.parseDouble(req.getParameter("prix"));
		String nom = req.getParameter("nom");
		String categorie = req.getParameter("cat");
		maPizza.setCode(code);
		maPizza.setNom(nom);
		maPizza.setPrix(prix);
		maPizza.setCategorie(CategoriePizza.valueOf(categorie.toUpperCase()));
		if(TestExist(maPizza)){
			monStockagePizza.updateTobject(maPizza, null);
		}else{
			monStockagePizza.saveTobject(maPizza);
		}
		resp.sendRedirect(req.getContextPath()+"/pizzas/list");
	}
	

	
	
}
