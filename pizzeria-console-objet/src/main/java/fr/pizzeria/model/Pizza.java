package fr.pizzeria.model;

import java.io.File;
import java.io.Serializable;

public class Pizza implements ExportInterface<Pizza> {

	
	private static int NBPIZZA;
	private static int IDPIZ;

	private int id;
	private String code;
	private String nom;
	private double prix;
	private CategoriePizza categorie;

	// Constructor
	public Pizza(String code, String nom, double prix) {
		super();
		setId(IDPIZ + 1);
		this.code = code.toUpperCase();
		this.nom = nom.replace('_', ' ');
		this.prix = prix;
		NBPIZZA++;
		IDPIZ++;
	}
	public Pizza(String code, String nom, double prix, CategoriePizza categorie) {
		super();
		setId(IDPIZ + 1);
		this.code = code.toUpperCase();
		this.nom = nom.replace('_', ' ');
		this.prix = prix;
		this.categorie = categorie;
		NBPIZZA++;
		IDPIZ++;
	}

	public Pizza(int i) {
		this.id = i;
	}

	// getter & setter

	public int getId() {
		return id;
	}

	public static void setNbPizza(int nbPizza) {
		Pizza.NBPIZZA = nbPizza;
	}

	public static int getNbPizza() {
		return NBPIZZA;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.toUpperCase();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom.replace('_', ' ');
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	

	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return this.getCode() + " " + this.getNom() + " " + this.getPrix()+" "+this.getCategorie();
	}
	@Override
	public String toStringXml() {
		// TODO Auto-generated method stub
		return "\t\t<Code>"+this.getCode() + "</Code>\n\t\t<Nom>" + this.getNom() + "</Nom>\n\t\t<Prix>" + this.getPrix()+"</Prix>\n\t\t<Categorie>"+this.getCategorie()+"</Categorie>\n";
	}
	
	
	
	@Override
	public Pizza importXML(File myFile) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
