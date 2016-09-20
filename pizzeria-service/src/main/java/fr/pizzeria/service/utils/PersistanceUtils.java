package fr.pizzeria.service.utils;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaJPA;



public class PersistanceUtils {
private static PersistanceUtils instanceUnique = new PersistanceUtils();
    
    private Stockage<Pizza> stockagePizza = new StockagePizzaJPA();
    
    // etape 1 - constructeur privé
    private PersistanceUtils() {
    }
    
    // etape 2 - accéder à l'instance unique depuis l'extérieur de la classe
    public static PersistanceUtils getInstance() {
        return instanceUnique;
    }
    
    public Stockage<Pizza> getStockagePizza() {
        return stockagePizza;
    }

}
