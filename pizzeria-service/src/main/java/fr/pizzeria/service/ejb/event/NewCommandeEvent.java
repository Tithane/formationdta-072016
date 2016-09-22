package fr.pizzeria.service.ejb.event;

import fr.pizzeria.model.Commande;

public class NewCommandeEvent {
	private Commande commandeCree ;

	public NewCommandeEvent(Commande commandeCree) {
		super();
		this.commandeCree = commandeCree;
	}

	public Commande getCommandeCree() {
		return commandeCree;
	}

	public void setCommandeCree(Commande commandeCree) {
		this.commandeCree = commandeCree;
	}
	
}
