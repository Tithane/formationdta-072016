package fr.pizzeria.exception;

public class SaisieCodeException extends Exception {

	private static final String MSG = "Veuillez saisir un code de 3 lettres !";

	public SaisieCodeException() {
		super(MSG);
		// TODO Auto-generated constructor stub
	}

	public SaisieCodeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SaisieCodeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SaisieCodeException(Throwable cause) {
		super(MSG, cause);
		// TODO Auto-generated constructor stub
	}

}
