package dta.pizzeria.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;

@Repository
public class PizzaJdbc implements Stockage<Pizza> {
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public PizzaJdbc(DataSource datasource) {
	this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public Pizza getPizza(ResultSet result)throws SQLException{
		Pizza pizza = new Pizza();
		pizza.setId(result.getInt("id"));
		pizza.setCode(result.getString("code"));
		pizza.setNom(result.getString("nom"));
		pizza.setPrix(result.getDouble("prix"));
		return pizza;
	}
	@Override
	public Collection<Pizza> findAll() {
		String sql = "SELECT * FROM PIZZA ";
		return this.jdbcTemplate.query(sql,(result, arg1) -> getPizza(result));
	}
	
	

	@Override
	public void saveTobject(Pizza newTobject) {
		String sql = "INSERT INTO PIZZA (code,nom,prix) VALUES(?,?,?)";
		this.jdbcTemplate.update(sql, newTobject.getCode(),newTobject.getNom(),newTobject.getPrix());
		
	}

	@Override
	public void updateTobject(Pizza editTobject, String code) {
		String sql = "UPDATE PIZZA SET code = ? ,nom = ? ,prix = ? WHERE id = ?";
		this.jdbcTemplate.update(sql, editTobject.getCode(),editTobject.getNom(),editTobject.getPrix(),editTobject.getId());
	}

	@Override
	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub
		
	}
	
	public void saveAll(List<Pizza> pizzas){
		pizzas.forEach(this::saveTobject);
	}

	
	

}
