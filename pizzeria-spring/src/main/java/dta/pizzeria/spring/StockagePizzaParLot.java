package dta.pizzeria.spring;

import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.model.Pizza;

@Repository
public class StockagePizzaParLot {

	@Autowired
	PizzaJdbc stockagePizza;
	
	
	

	@Transactional(rollbackFor=Exception.class)
	public void insererPizzaParLot(List<Pizza> pizzas, Integer size) {
		ListUtils.partition(pizzas, size).forEach(stockagePizza::saveAll);
	}

}
