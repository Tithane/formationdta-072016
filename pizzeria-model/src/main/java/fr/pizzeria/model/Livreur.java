package fr.pizzeria.model;

import javax.persistence.Entity;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

@Entity
public class Livreur extends AbstractPersonne {
	private static final int SEUIL = -100;
	
	public static int getSeuil() {
		return SEUIL;
	}
	
	public Livreur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Livreur(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public Livreur(String nom, String prenom) {
		super(nom, prenom);
		// TODO Auto-generated constructor stub
	}
	public void crediterCompte(double montant) throws CreditException {
		double nouveauSolde = this.solde + montant;
		this.solde = nouveauSolde;

	}
	public void debiterCompte(double montant) throws DebitException {
		double nouveauSolde = this.solde + montant;
		if(nouveauSolde <= SEUIL){
			throw new DebitException();
		}
		this.solde = nouveauSolde;

	}
}
