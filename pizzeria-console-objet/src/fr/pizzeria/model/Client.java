package fr.pizzeria.model;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

public class Client extends AbstractPersonne{
	
	private static final int SEUIL = 5000 ;
	

	public static int getSeuil() {
		return SEUIL;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public Client(String nom, String prenom) {
		super(nom, prenom);
		this.solde = 50;
		// TODO Auto-generated constructor stub
	}
	public void crediterCompte(double montant) throws CreditException {
		double nouveauSolde = this.solde + montant;
		if(nouveauSolde >= SEUIL){
			throw new CreditException();
		}
		this.solde = nouveauSolde;

	}
	public void debiterCompte(double montant) throws DebitException {
		double nouveauSolde = this.solde + montant;
		if(nouveauSolde < 0){
			throw new DebitException();
		}
		this.solde = nouveauSolde;

	}




}
