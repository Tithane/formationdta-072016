package web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.ejb.PizzaServiceEJB;

@Path("/pizzas")
public class PizzaResource{
	
	@PersistenceContext
	EntityManager em;
	List<Pizza> pizzas = new ArrayList<>();
	@EJB private PizzaServiceEJB servicePizza;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Pizza> findAll() {
		return servicePizza.findAll();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void saveTobject(Pizza newTobject) {
		servicePizza.saveTobject(newTobject);
		
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void updateTobject(Pizza editTobject) {
		servicePizza.updateTobject(editTobject, null);
	}
	
	@DELETE
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("/{code}")
	public void deleteTobject(@PathParam(value = "code") String ancienCode) {
		servicePizza.deleteTobject(ancienCode);
		
		
	}

	
}
