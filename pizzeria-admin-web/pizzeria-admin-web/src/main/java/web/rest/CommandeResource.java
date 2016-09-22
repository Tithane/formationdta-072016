package web.rest;

import java.util.Collection;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pizzeria.model.Commande;
import fr.pizzeria.service.ejb.CommandeServiceEJB;


@Path("/commande")
public class CommandeResource {
	
	@PersistenceContext
	EntityManager em;
	@EJB
	private CommandeServiceEJB serviceCommande;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Commande> findAll() {
		return serviceCommande.findAll();
	}

}
