package fr.pizzeria.exception;

import fr.pizzeria.model.Client;

public class DebitException extends Exception {
	private static final String MSG = "Solde insuffisant";

	public DebitException() {
		super(MSG);
		// TODO Auto-generated constructor stub
	}
	
	

}
