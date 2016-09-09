package fr.pizzeria.model;

public enum EnumStatut {
	EN_COURS ("En cours"),
	LIVRAISON ("Livraison"),
	TERMINER ("Terminer");

	private String name;;

	EnumStatut(String name) {
		this.name = name ;
	}

	@Override
	public String toString() {
		return name;
	}

}
