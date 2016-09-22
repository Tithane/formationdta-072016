package fr.pizzeria.model;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pizza implements ExportInterface<Pizza>,Serializable {

	
	private static int NBPIZZA;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(length=3)
	private String code;
	private String nom;
	private Double prix;
	private String url;
	
	@Enumerated(EnumType.STRING)
	private CategoriePizza categorie;

	// Constructor
	public Pizza(String code, String nom, double prix) {
		super();
		this.code = code.toUpperCase();
		this.nom = nom.replace('_', ' ');
		this.prix = prix;
		NBPIZZA++;
	}
	public Pizza(String code, String nom, double prix, CategoriePizza categorie) {
		super();
		this.code = code.toUpperCase();
		this.nom = nom.replace('_', ' ');
		this.prix = prix;
		this.categorie = categorie;
		NBPIZZA++;
	}
	
	

	public Pizza(Integer id, String code, String nom, Double prix, String url, CategoriePizza categorie) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.url = url;
		this.categorie = categorie;
	}
	public Pizza(Integer id, String code, String nom, Double prix, CategoriePizza categorie) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
	}
	public Pizza(int i) {
		this.id = i;
	}

	// getter & setter

	public Pizza() {
		// TODO Auto-generated constructor stub
	}
	public Pizza(String code) {
		super();
		this.code = code;
	}
	public Integer getId() {
		return id;
	}

	public static void setNbPizza(int nbPizza) {
		Pizza.NBPIZZA = nbPizza;
	}

	public static int getNbPizza() {
		return NBPIZZA;
	}

	public void setId(Integer id) {
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
	

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return this.getId()+" - "+ this.getCode() + " " + this.getNom() + " " + this.getPrix()+" "+this.getCategorie();
	}
	
	@Override
	public String toStringXml() {
		// TODO Auto-generated method stub
		return "\t\t<Code>"+this.getCode() + "</Code>\n\t\t<Nom>" + this.getNom() + "</Nom>\n\t\t<Prix>" + this.getPrix()+"</Prix>\n\t\t<Categorie>"+this.getCategorie()+"</Categorie>\n";
	}
	
	public String toStringSql(){
		return "INSERT INTO `pizza` (`code`, `nom`, `prix`, `categorie`) VALUES ('"+this.getCode()+"','"+this.getNom()+"', "+this.getPrix()+",'"+this.getCategorie().name()+"');";
		
	}
	

	
	

}
