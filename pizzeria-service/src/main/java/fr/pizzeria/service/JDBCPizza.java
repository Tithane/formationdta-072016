package fr.pizzeria.service;

import java.awt.Transparency;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class JDBCPizza implements Stockage<Pizza> {
	
	public List<Pizza> pizzas = new ArrayList<Pizza>();
	
	
	public JDBCPizza() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Collection<Pizza> findAll() {
		// TODO Auto-generated method stub
		try {
			pizzas.clear();
			String sql = "SELECT * FROM pizza";
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcpizzeria","root","");
			
			Statement st = connect.createStatement();
			ResultSet result = st.executeQuery(sql);
			while (result.next()) {
				Pizza pizza = new Pizza();
				pizza.setId(result.getInt("id"));
				pizza.setCode(result.getString("code"));
				pizza.setNom(result.getString("nom"));
				pizza.setPrix(result.getDouble("prix"));
				pizza.setCategorie(CategoriePizza.valueOf(result.getString("categorie")));
				pizzas.add(pizza);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pizzas;
	}

	@Override
	public void saveTobject(Pizza newTobject) {
		String sql = "INSERT INTO pizza(code,nom,prix,categorie) VALUES(?,?,?,?)";
		Connection connect;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcpizzeria","root","");
			Statement st = connect.createStatement();
			PreparedStatement request = connect.prepareStatement(sql);
			request.setString(1,newTobject.getCode());
			request.setString(2,newTobject.getNom());
			request.setDouble(3,newTobject.getPrix());
			request.setString(4,newTobject.getCategorie().name());
			
			request.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		

	}

	@Override
	public void updateTobject(Pizza editTobject, String code) {
		String sql = "Update pizza SET code = ? , nom = ? , prix = ? WHERE id = ?   ";
		Connection connect;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcpizzeria","root","");
			PreparedStatement request = connect.prepareStatement(sql);
			request.setString(1,editTobject.getCode());
			request.setString(2,editTobject.getNom());
			request.setDouble(3,editTobject.getPrix());
			request.setInt(4,editTobject.getId());
			request.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub

	}

}
