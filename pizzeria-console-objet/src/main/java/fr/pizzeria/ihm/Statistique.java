package fr.pizzeria.ihm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.CompteStat;
import fr.pizzeria.model.Livreur;

public class Statistique extends Action {

	public Statistique( IhmHelper helper) {
		super("Statistiques", helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		List<CompteStat> liste = new ArrayList<>();
		List<Client> liste_client = (List<Client>) helper.getStockageClient().findAll();
		List<Livreur> liste_livreur = (List<Livreur>) helper.getStockageLivreur().findAll();
		liste.addAll(liste_client);
		liste.addAll(liste_livreur);
		int nb_compte = liste.size();
		double  somme = 0, 
				soldeMin = 0,
				soldeMax = 0,
				moyenne = 0;
		
		 somme = liste.stream().filter(t -> t.getSolde() != 0).collect(Collectors.summingDouble(CompteStat::getSolde));
		 moyenne = liste.stream().filter(t -> t.getSolde() != 0).collect(Collectors.averagingDouble(CompteStat::getSolde));
		 soldeMin = liste.stream().collect(Collectors.minBy(Comparator.comparing(CompteStat::getSolde))).get().getSolde();
		 soldeMax = liste.stream().collect(Collectors.maxBy(Comparator.comparing(CompteStat::getSolde))).get().getSolde();
		 
//		for (CompteStat compte : liste) {
//			somme = somme + compte.getSolde();
//			if(compte.getSolde() <= soldeMin){
//				soldeMin = compte.getSolde();
//			}else if(compte.getSolde()>= soldeMax){
//				soldeMax = compte.getSolde();
//			}
//		}
		System.out.println("Nombre de comptes = "+nb_compte);
		System.out.println("Total Solde = "+somme+"€");
		System.out.println("Moyenne Solde = "+moyenne+"€");
		System.out.println("Solde le plus faible = "+soldeMin+"€");
		System.out.println("Solde le plus élevé = "+soldeMax+"€");
	}

	
}
