package web.general;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import fr.pizzeria.service.ejb.event.NewCommandeEvent;

@Stateless
public class WebSocket {
	private Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	public String message(String message, Session session) {
		return message;

	}

	@OnOpen
	public void open(Session session, EndpointConfig config) {
		clients.add(session);
		Logger.getAnonymousLogger().log(Level.INFO, "Nouveau client de connecté.");
	}

	@OnClose
	public void close(CloseReason closeReason) {

	}

	public void onNewCommande(@Observes NewCommandeEvent event) {
		clients.forEach(client -> {
			try {
				client.getBasicRemote().sendObject(event.getCommandeCree());
			} catch (IOException e) {
				Logger.getAnonymousLogger().log(Level.SEVERE, "Une erreur est survenu",e);
			} catch (EncodeException e) {
				Logger.getAnonymousLogger().log(Level.SEVERE, "Une erreur est survenu",e);
			}
		});
	}
}
