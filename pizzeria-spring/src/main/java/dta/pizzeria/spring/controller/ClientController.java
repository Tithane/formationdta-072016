package dta.pizzeria.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dta.pizzeria.spring.event.CreateClientEvent;
import dta.pizzeria.spring.model.ResponseClient;
import dta.pizzeria.spring.model.SoldeParam;
import dta.pizzeria.spring.service.ClientRepository;
import fr.pizzeria.model.Client;

@RestController
@RequestMapping("/clients")
@CrossOrigin
public class ClientController {
	
	//@Autowired private Stockage<Client> stockageC;
	@Autowired ClientRepository clientRepo;
	
	@Autowired private ApplicationEventPublisher publisher;
		
	@RequestMapping(method=RequestMethod.GET)
	public String getClients(){
		String myJsonObject = "echec";
		//Jackson  => convert in Json
		ObjectMapper mapper =new ObjectMapper();
		List<Client> mesClients = new ArrayList<>();
		mesClients.addAll(clientRepo.findAll());
		try {
			myJsonObject = mapper.writeValueAsString(mesClients);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myJsonObject;
	}
	
	@RequestMapping(path="/{id}/solde", method=RequestMethod.POST, consumes = "application/json", produces="application/json")
	public ResponseClient updateSoldeClient(@PathVariable String id,@RequestBody SoldeParam monObjet){
		ResponseClient response = new ResponseClient();
		Integer cltId;
		Double cltSolde;
		String action = monObjet.getOperation();
		Boolean etat = false;
		try{
			
			cltId = Integer.parseInt(id);
			cltSolde = monObjet.getMontant();
			Client client = clientRepo.findOne(cltId);
			client.setId(cltId);
			if(action.equals("CREDIT")){
				client.crediterCompte(cltSolde);
			}else if(action.equals("DEBIT")){
				client.debiterCompte(cltSolde);
			}
			clientRepo.save(client);
			//stockageC.updateTobject(client, null);
			
			publisher.publishEvent(new CreateClientEvent(client));
			etat = true ;
			response.setSuccess(etat);
			response.setSolde(client.getSolde());
			
		}catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Erreur : ", e);
			response.setSuccess(etat);
			response.setMessage(e.getMessage());
		}
		
		return response;
		
	}

}
