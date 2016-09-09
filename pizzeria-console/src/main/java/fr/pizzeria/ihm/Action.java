package fr.pizzeria.ihm;

import fr.pizzeria.model.Client;

public abstract class Action {

	private String libelle;
	protected IhmHelper helper;
	protected Client client;

	public Action(String libelle, IhmHelper helper) {
		super();
		this.libelle = libelle;
		this.helper = helper;
	}
	public Action(String libelle, IhmHelper helper, Client client) {
		super();
		this.client = client;
		this.libelle = libelle;
		this.helper = helper;
	}

	public void execute() {

	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	

	

}
