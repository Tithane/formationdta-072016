package dta.pizzeria.spring.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {
	private static final Logger LOG = Logger.getLogger(WebApplicationInitializer.class.getName()) ;

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		LOG.log(Level.INFO, "Démarrage du serveur");
		
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfig.class);
		
		ServletRegistration.Dynamic dispatcher = context.addServlet("dispatcher", new DispatcherServlet(webContext));
		dispatcher.setLoadOnStartup(1);
		
		dispatcher.addMapping("/mvc/api/*");
		
		context.addListener(new ContextLoaderListener(webContext));
		
	}

}
