package fr.pizzeria.exception;

public class SaisieEntierException extends Exception {

	private static final String MSG = "Veuillez saisir un entier !";

	public SaisieEntierException() {
		super(MSG);
		// TODO Auto-generated constructor stub
	}

	public SaisieEntierException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SaisieEntierException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SaisieEntierException(Throwable cause) {
		super(MSG, cause);
		// TODO Auto-generated constructor stub
	}

}
