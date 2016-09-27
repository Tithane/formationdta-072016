package dta.pizzeria.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.service.Stockage;

@Component
public class SpringConsole {
@Autowired private Stockage<?> stockage;
    
    
    public void afficherToutesLesPizzas() {
        stockage.findAll().forEach(System.out::println);
    }
}
