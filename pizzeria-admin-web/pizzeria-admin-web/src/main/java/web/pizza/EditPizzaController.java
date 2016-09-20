package web.pizza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.ejb.PizzaServiceEJB;

@WebServlet("/pizzas/edit")
public class EditPizzaController extends HttpServlet {

	Collection<Pizza> liste_pizza = new ArrayList<>();
//	@Inject
//	@Named("stockagePizzaJPA")
//	private Stockage<Pizza> stockagePizza;
	@EJB private PizzaServiceEJB stockagePizza ;

	public boolean TestExist(Pizza pizza) {
		liste_pizza = stockagePizza.findAll();
		AtomicBoolean etat = new AtomicBoolean();
		etat.set(false);
		liste_pizza.stream().forEach(pizzaBase -> {
			if (pizzaBase.getCode().equals(pizza.getCode())) {
				etat.set(true);
			}
			;
		});
		return etat.get();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/editPizza.jsp");
		CategoriePizza[] catPizza = CategoriePizza.values();
		Pizza maPizza = new Pizza();
		if (req.getParameter("code") != null) {
			Optional<Pizza> pizzaOpt = stockagePizza.findAll().stream()
					.filter(laPizza -> laPizza.getCode().equals(req.getParameter("code"))).findFirst();
			if (pizzaOpt.isPresent()) {
				maPizza = pizzaOpt.get();
			} else {
				req.setAttribute("maPizza", maPizza);
			}

			req.setAttribute("maPizza", maPizza);
		} else {
			req.setAttribute("maPizza", null);
		}
		req.setAttribute("catPizza", catPizza);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Pizza maPizza = new Pizza();
		String url = "noImage.jpg";
		if (!req.getParameter("url").isEmpty()) {
			url = req.getParameter("url");
		}
		String code = req.getParameter("code");
		Double prix = Double.parseDouble(req.getParameter("prix"));
		String nom = req.getParameter("nom");
		String categorie = req.getParameter("cat");
		maPizza.setCode(code);
		maPizza.setNom(nom);
		maPizza.setPrix(prix);
		maPizza.setCategorie(CategoriePizza.valueOf(categorie.toUpperCase()));
		if (req.getParameter("id") != null) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			maPizza.setId(id);
			if (!req.getParameter("url").isEmpty()) {
				url = req.getParameter("url");
				maPizza.setUrl(url);
			}
			stockagePizza.updateTobject(maPizza, null);
		} else {
			maPizza.setUrl(url);
			stockagePizza.saveTobject(maPizza);
		}
		resp.sendRedirect(req.getContextPath() + "/pizzas/list");
	}

}
