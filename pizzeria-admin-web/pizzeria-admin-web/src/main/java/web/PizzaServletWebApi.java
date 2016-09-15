package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.functors.IfTransformer;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.StockagePizzaJPA;

public class PizzaServletWebApi extends HttpServlet {
	
	Collection<Pizza> liste_pizza = new ArrayList<>();
	StockagePizzaJPA monStockagePizza = new StockagePizzaJPA();
	PrintWriter monWritter;
	
	public void MesPizzas(){
		liste_pizza = monStockagePizza.findAll();
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
	
	public Map<String, String> GetMyForm(HttpServletRequest req) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String tab[] = br.readLine().split("&");
		Map<String,String> myForm = new HashMap<>();
		for(int i =0;i<tab.length;i++){
			String tab2[];
			tab2=tab[i].split("=");
			myForm.put(tab2[0], tab2[1]);
		}
		return myForm;
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		monWritter = resp.getWriter();
		String code = req.getParameter("code");
		Pizza maPizza = new Pizza(code);
		if(TestExist(maPizza)){
			monStockagePizza.deleteTobject(maPizza.getCode());
			monWritter.write("Pizza Supprimée");
		}else{
			monWritter.write("La Pizza n'existe pas");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		monWritter = resp.getWriter();
		MesPizzas();
		if(liste_pizza.isEmpty()){
			monWritter.write("Aucune Pizza");
		}else{
			liste_pizza.stream().forEach(pizza->{
				monWritter.write(pizza.toString()+"\n");
			});
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		monWritter = resp.getWriter();
		Map<String, String> myForm = GetMyForm(req);
		String code =  myForm.get("code");
		Double prix = Double.parseDouble(myForm.get("prix"));
		String nom = myForm.get("nom");
		String categorie = myForm.get("cat");
		Pizza maPizza = new Pizza(code, nom, prix,CategoriePizza.valueOf(categorie));
		if(TestExist(maPizza)){
			monWritter.write("La Pizza existe déja");
		}else{
			monStockagePizza.saveTobject(maPizza);
			monWritter.write("Pizza Ajoutée" );
		}
		
				
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		monWritter = resp.getWriter();
		Map<String, String> myForm = GetMyForm(req);
		Integer id = Integer.parseInt(myForm.get("id"));
		String code =  myForm.get("code");
		Double prix = Double.parseDouble(myForm.get("prix"));
		String nom = myForm.get("nom");
		String categorie = myForm.get("cat");
		Pizza maPizza = new Pizza(id,code, nom, prix,CategoriePizza.valueOf(categorie));
		if(TestExist(maPizza)){
			//monWritter.write(maPizza.toString());
			monStockagePizza.updateTobject(maPizza, null);
			monWritter.write("Pizza mise à jour");
		}else{
			monWritter.write("La Pizza n'existe pas");
		}
	}
	
	
}
