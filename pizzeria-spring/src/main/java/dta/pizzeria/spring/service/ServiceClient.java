package dta.pizzeria.spring.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.pizzeria.model.Client;
import fr.pizzeria.service.Stockage;

@Repository
public class ServiceClient implements Stockage<Client> {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ServiceClient(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public Client createClient(ResultSet result) throws SQLException {
		Client client = new Client();
		client.setId(result.getInt("id"));
		client.setNom(result.getString("nom"));
		client.setPrenom(result.getString("prenom"));
		client.setSolde(result.getDouble("solde"));
		client.setMail(result.getString("mail"));
		return client;
	}

	@Override
	public Collection<Client> findAll() {
		String sql = "SELECT * FROM ABSTRACTPERSONNE ap ,CLIENT clt WHERE ap.id=clt.id ";
		return this.jdbcTemplate.query(sql, (result, arg1) -> createClient(result));
	}

	@Override
	public void saveTobject(Client newTobject) {

	}

	@Override
	public void updateTobject(Client editTobject, String code) {
		String sql = "UPDATE CLIENT clt,ABSTRACTPERSONNE ap SET ap.solde = ? WHERE ap.id = clt.id AND ap.id = ? ";
		this.jdbcTemplate.update(sql, editTobject.getSolde(), editTobject.getId());
	}

	@Override
	public void deleteTobject(String ancienCode) {
		// TODO Auto-generated method stub

	}

}
