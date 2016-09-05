package fr.pizzeria.model;

import org.junit.Assert;
import org.junit.Test;

import fr.pizzeria.exception.CreditException;

public class ClientTest {
	public void init(){
		
	}
	
	
	@Test
	//Tout ce passe bien
	public void testCrediterCompte_Cas01() throws CreditException{
		double soldeBase = 150;
		double ajout = 300; 
		Client client = new Client("William","Larson");
		client.setSolde(soldeBase);
		client.crediterCompte(ajout);
		Assert.assertTrue(soldeBase+ajout == client.getSolde());
		
	}
	
	@Test (expected = CreditException.class)
	//Une CréditException est retourné
	public void testCrediterCompte_Cas02() throws CreditException{
		double soldeBase = 5000;
		double ajout = 300; 
		Client client = new Client("William","Larson");
		client.setSolde(soldeBase);
		client.crediterCompte(ajout);
	}

}
