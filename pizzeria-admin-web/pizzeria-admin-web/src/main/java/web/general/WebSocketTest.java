package web.general;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

//import fr.pizzeria.model.OrmUtils;
import fr.pizzeria.service.ejb.event.NewCommandeEvent;

@ServerEndpoint(value="/websocket")
@ApplicationScoped
public class WebSocketTest {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public String message(String message, Session session) {
		return message;

	}

	@OnOpen
	public void open(Session session, EndpointConfig config) {
		clients.add(session);
		Logger.getAnonymousLogger().log(Level.INFO, "Nouveau client de connect�.");
	}

	@OnClose
	public void close(CloseReason closeReason) {

	}

	public void onNewCommande(@Observes NewCommandeEvent event) {
		ObjectMapper mapper = new ObjectMapper();
		clients.forEach(client -> {
			/*try {
				client.getBasicRemote().sendText(mapper.writeValueAsString(OrmUtils.initializeAndUnproxy(event.getCommandeCree())
						));
			} catch (IOException e) {
				Logger.getAnonymousLogger().log(Level.SEVERE, "Une erreur est survenu",e);
			}*/
		});
	}
}
