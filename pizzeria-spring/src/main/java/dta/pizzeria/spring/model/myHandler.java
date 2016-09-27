package dta.pizzeria.spring.model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import dta.pizzeria.spring.event.CreateClientEvent;
import fr.pizzeria.model.Client;

public class myHandler implements WebSocketHandler, ApplicationListener<CreateClientEvent> {
	public static Set<WebSocketSession> mesClients = new HashSet<>();
	private Client monClient = new Client();
	

	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
		Logger.getAnonymousLogger().log(Level.INFO, "Client "+arg0.getId()+" déconnecté...");
		mesClients.remove(arg0);
				
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
		Logger.getAnonymousLogger().log(Level.INFO, "Client "+arg0.getId()+" Connecté...");
		mesClients.add(arg0);
		
	}

	@Override
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {
		mesClients.forEach(clt -> {
			try {
				clt.sendMessage(arg1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onApplicationEvent(CreateClientEvent arg0) {
		this.monClient = (Client)arg0.getSource();
		mesClients.forEach(clt -> {
			try {
				clt.sendMessage(new TextMessage(monClient.toString()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
