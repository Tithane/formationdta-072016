package fr.pizzeria.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import fr.pizzeria.model.Pizza;

public class InsertionPizza implements Stockage<String>{

	List<String> liste = new ArrayList<>();

	@Override
	public Collection<String> findAll() {
		// TODO Auto-generated method stub
		try {
			Files.lines(Paths.get("data","Pizza.txt")).forEach(p -> liste.add(p));
		} catch (Exception e) {
			System.err.println("Erreur :" + e.getMessage());
		}
		return liste;
	}

	@Override
	public void saveTobject(String newTobject) {
	}
	
	
	public void saveTobject(Collection<String> newTobject) throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcpizzeria","root","");
		connect.setAutoCommit(false);
		
		try {
			List<String> liste = new ArrayList<>();
			liste.addAll(newTobject);
			List<List<String>> partition = ListUtils.partition(liste, 3);
			for (List<String> list : partition) {
				for (String string : list) {
					System.out.println(string);
					Statement statement = connect.createStatement();
					statement.executeUpdate(string);
					statement.close();
				}
				connect.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		connect.rollback();	
		
		}
		
	}

	@Override
	public void updateTobject(String editTobject, String code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub
		
	}

}
