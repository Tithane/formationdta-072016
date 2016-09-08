package fr.pizzeria.model;

import java.io.File;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

public abstract class AbstractPersonne implements CompteStat , ExportInterface {
	protected static int nbClient;
	protected static int idClt;
	protected static int SEUIL;
	
	protected int id;
	protected String nom;
	protected String prenom;
	protected double solde;

	public AbstractPersonne(String nom, String prenom) {
		super();
		prenom = prenom.substring(0, 1).toUpperCase()+prenom.substring(1);
		this.nom = nom.toUpperCase();
		this.prenom = prenom;
		this.id = idClt++;
		nbClient++;
	}
	
	

	public int getSEUIL() {
		return SEUIL;
	}



	public AbstractPersonne() {
		super();
	}
	public AbstractPersonne(int id){
		this.id =id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		prenom = prenom.substring(0, 1).toUpperCase()+prenom.substring(1);
		this.prenom = prenom;
	}

	public double getSolde() {
		return solde;
	}
	

	public void setSolde(double solde) {
		this.solde = solde;
	}
	

	public void crediterCompte(double montant) throws CreditException {
	}

	public void debiterCompte(double montant) throws DebitException {
	}

	@Override
	public String toString() {
		return id + " -> " + nom + " " + prenom + " (" + solde + " €)";
	}


	@Override
	public String toStringXml() {
		return "\t\t<Identifiant>"+this.getId() + "</Identifiant>\n\t\t<Non>" + this.getNom() + "</Non>\n\t\t<Prenom>" + this.getPrenom()+"</Prenom>\n\t\t<Solde>"+this.getSolde()+"</Solde>\n";
		
	}



	@Override
	public String toStringSql() {
		// TODO Auto-generated method stub
		return null;
	}
	
	



	
	
	
	

}
