package fr.pizzeria.model;

import java.beans.Encoder;
import java.beans.ExceptionListener;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.Statement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Commande implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String numero_commande ;
	@Enumerated
	private EnumStatut statut;
	
	//utilisation de LocalDate Complexe avec JPA
	private Calendar date_commande;
	@OneToOne
	private Livreur livreur;
	@OneToOne
	private Client client;
	
	@ManyToMany
	@JoinTable(name="commande_pizza",
		joinColumns =
	@JoinColumn(name="commande_id",referencedColumnName="id"),
		inverseJoinColumns =
	@JoinColumn(name="pizza_id",referencedColumnName="id")
	)
	private Set<Pizza> pizzas;
	
	public Commande() {
		super();
	}
	
	
	
	public Commande(String numero_commande, Calendar date_commande, Client client_id, Set<Pizza> pizzas) {
		super();
		this.numero_commande = numero_commande;
		this.date_commande = date_commande;
		this.client = client_id;
		this.pizzas = pizzas;
		this.statut = EnumStatut.EN_COURS;
	}



	public Commande(Integer id, String numero_commande, EnumStatut statut, Calendar date_commande, Livreur livreur_id,
			Client client_id) {
		super();
		this.id = id;
		this.numero_commande = numero_commande;
		this.statut = statut;
		this.date_commande = date_commande;
		this.livreur = livreur_id;
		this.client = client_id;
	}
	

	public Commande(Integer id, String numero_commande, EnumStatut statut, Calendar date_commande, Livreur livreur_id,
			Client client_id, Set<Pizza> pizzas) {
		super();
		this.id = id;
		this.numero_commande = numero_commande;
		this.statut = statut;
		this.date_commande = date_commande;
		this.livreur = livreur_id;
		this.client = client_id;
		this.pizzas = pizzas;
	}

	
	public Set<Pizza> getPizzas() {
		return pizzas;
	}


	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	public void setPizza(Pizza pizza){
		this.pizzas.add(pizza);
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero_commande() {
		return numero_commande;
	}
	public void setNumero_commande(String numero_commande) {
		this.numero_commande = numero_commande;
	}
	public EnumStatut getStatut() {
		return statut;
	}
	public void setStatut(EnumStatut statut) {
		this.statut = statut;
	}
	public Calendar getDate_commande() {
		return date_commande;
	}
	public void setDate_commande(Calendar date_commande) {
		this.date_commande = date_commande;
	}
	public Livreur getLivreur_id() {
		return livreur;
	}
	public void setLivreur_id(Livreur livreur_id) {
		this.livreur = livreur_id;
	}
	public Client getClient_id() {
		return client;
	}
	public void setClient_id(Client client_id) {
		this.client = client_id;
	}


	
	


	
	
}
