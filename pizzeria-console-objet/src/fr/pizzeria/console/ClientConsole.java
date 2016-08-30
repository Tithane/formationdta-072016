package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.model.Client;

public class ClientConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.setNom("pourquoi");
		client.setPrenom("parceque");
		System.out.println("solde client = "+client.getSolde());
		
		Scanner sc = new Scanner(System.in);
		double saisie1 = sc.nextDouble();
		try {
			client.crediterCompte(saisie1);
		} catch (CreditException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("solde client = "+client.getSolde());
		double saisie2 = sc.nextDouble();
		try {
			client.debiterCompte(saisie2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(client);

	}

}
