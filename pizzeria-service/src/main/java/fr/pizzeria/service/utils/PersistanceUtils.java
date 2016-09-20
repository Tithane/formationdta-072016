package fr.pizzeria.service.utils;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaJPA;



public class PersistanceUtils {
private static PersistanceUtils instanceUnique = new PersistanceUtils();
    
    private Stockage<Pizza> stockagePizza = new StockagePizzaJPA();
    
    // etape 1 - constructeur priv�
    private PersistanceUtils() {
    }
    
    // etape 2 - acc�der � l'instance unique depuis l'ext�rieur de la classe
    public static PersistanceUtils getInstance() {
        return instanceUnique;
    }
    
    public Stockage<Pizza> getStockagePizza() {
        return stockagePizza;
    }

}
