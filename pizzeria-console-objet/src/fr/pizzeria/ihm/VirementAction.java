package fr.pizzeria.ihm;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;
import fr.pizzeria.model.AbstractPersonne;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.CompteStat;
import fr.pizzeria.model.Livreur;

public class VirementAction extends Action {

	public VirementAction(IhmHelper helper) {
		super("Virement", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		List<AbstractPersonne> liste = new ArrayList<>();
		List<Client> liste_client= (List<Client>) helper.getStockageClient().findAll();
		//List<Livreur>liste_livreur = (List<Livreur>) helper.getStockageLivreur().findAll();
		liste.addAll(liste_client);
		//liste.addAll(liste_livreur);
		System.out.println("Veuiller choisir le client à débiter\n");
		for (AbstractPersonne personne : liste) {
			if(personne.getSolde()>0){
				System.out.println(personne);
			}
		}
		int saisie1 = helper.getScanner().nextInt();
		System.out.println("Veuiller choisir le client à créditer\n");
		for (AbstractPersonne personne : liste) {
			if(personne.getSolde() < Client.getSeuil() && personne.getId() != saisie1){
				System.out.println(personne);
			}
		}
		int saisie2 = helper.getScanner().nextInt();
		System.out.println("Veuiller indiquer le montant\n");
		double saisie3 = helper.getScanner().nextDouble();
		String code = String.valueOf(saisie3);
		//AbstractPersonne personne1;
		//AbstractPersonne personne2;
		Client personne1 = new Client(saisie1);
		Client personne2 = new Client(saisie2);
		try{
			if(liste_client.get(liste_client.indexOf(personne1.getId())).getId() != -1
					&& liste_client.get(liste_client.indexOf(personne2.getId())).getId() != -1){
				helper.getStockageClient().updateTobject(personne1, "-"+code);
				helper.getStockageClient().updateTobject(personne2, code);
			}
		}catch(Exception e){
			System.out.println("Désolé une erreur c'est produite");
		}
		
		
	}
	
}
