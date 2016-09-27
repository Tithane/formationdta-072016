package fr.pizzeria.model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.digest.DigestUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.pizzeria.exception.CreditException;
import fr.pizzeria.exception.DebitException;

@Entity
public class Client extends AbstractPersonne {

	private static final int SEUIL = 5000;
	private String mail;
	private String password;
	
	

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}
	
	public void setPasswordEncrypt(String password) throws GeneralSecurityException{
		this.password = encodage(password);
	}

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
		// TODO Auto-generated constructor stub
	}
	

	public Client(String nom, String prenom, String mail, String password) throws GeneralSecurityException {
		super(nom, prenom);
		this.mail = mail;
		this.password = encodage(password);
	}

	public void crediterCompte(double montant) throws CreditException {
		double nouveauSolde = this.solde + montant;
		if (nouveauSolde >= SEUIL) {
			throw new CreditException();
		}
		this.solde = nouveauSolde;

	}

	public void debiterCompte(double montant) throws DebitException {
		double nouveauSolde = this.solde - montant;
		if (nouveauSolde < 0) {
			throw new DebitException();
		}
		this.solde = nouveauSolde;

	}
	
	public static String encodage(String mdp) throws GeneralSecurityException{
		return DigestUtils.sha512Hex(mdp);
	}

	@Override
	public String toString() {
		
		return super.toString()+" "+this.getMail()+" "+this.getPassword();
	}

	
	// En cours
//	public List<Client> xmlToClass(File fileClient) throws ParserConfigurationException, SAXException, IOException {
//		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		final DocumentBuilder builder = factory.newDocumentBuilder();
//		final Document document = builder.parse(fileClient);
//		final Element racine = document.getDocumentElement();
//		final NodeList racineNoeuds = racine.getChildNodes();
//
//		for (int i = 0; i < racineNoeuds.getLength(); i++) {
//			if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
//				final Node objet = racineNoeuds.item(i);
//			}
//		}
//		// lister les champs
//		Field[] champs;
//		if (Client.class.getSuperclass().getDeclaredFields().length != -1) {
//			champs = Client.class.getSuperclass().getDeclaredFields();
//		} else {
//			champs = Client.class.getDeclaredFields();
//		}
//		for (Field field : champs) {
//			if (!Modifier.isStatic(field.getModifiers())) {
//				System.out.println(field.getClass().getConstructors());
//			}
//		}
//		return null;
//
//	}

//	public static void main(String[] args)
//			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
//			InvocationTargetException, SAXException, IOException, ParserConfigurationException {
//		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		final DocumentBuilder builder = factory.newDocumentBuilder();
//		final Document document = builder.parse("data/Client.xml");
//		final Element racine = document.getDocumentElement();
//		final NodeList racineNoeuds = racine.getChildNodes();
//		List<Element> liste_objet = new ArrayList<>();
//
//		for (int i = 0; i < racineNoeuds.getLength(); i++) {
//			if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
//				Element objet = (Element) racineNoeuds.item(i);
//				liste_objet.add(objet);
//			}
//		}
//		Field[] champs;
//		if (Client.class.getSuperclass().getDeclaredFields().length != -1) {
//			champs = Client.class.getSuperclass().getDeclaredFields();
//
//		} else {
//			champs = Client.class.getDeclaredFields();
//		}
//		Client client = new Client();
//		Method monSetter;
//		for (Field field : champs) {
//			if (!Modifier.isStatic(field.getModifiers())) {
//				String nomChamp = field.getName();
//				String nomMethode = "set"
//						+ nomChamp.replace(nomChamp.substring(0, 1), nomChamp.substring(0, 1).toUpperCase());
//				monSetter = Client.class.getMethod(nomMethode, field.getType());
//				for (Element myObject : liste_objet) {
//					monSetter.invoke(client,myObject.getElementsByTagName(nomChamp));
//					System.out.println(myObject.getElementsByTagName(nomChamp));
//				}
//				System.out.println(client);
//			}
//
//		}
//	}

}
