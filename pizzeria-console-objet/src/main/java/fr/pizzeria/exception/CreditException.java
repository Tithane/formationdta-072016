package fr.pizzeria.exception;


import fr.pizzeria.model.Client;

public class CreditException extends Exception {
	
	private static final String MSG = "Seuil du solde limité à "+Client.getSeuil()+" €";

	public CreditException() {
		super(MSG);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
