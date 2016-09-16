package web.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		Integer compteur = (Integer) se.getSession().getServletContext().getAttribute("compteur");
		se.getSession().getServletContext().setAttribute("compteur", compteur + 1);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		Integer compteur = (Integer) se.getSession().getServletContext().getAttribute("compteur");
		se.getSession().getServletContext().setAttribute("compteur", compteur - 1);
		
	}

}
