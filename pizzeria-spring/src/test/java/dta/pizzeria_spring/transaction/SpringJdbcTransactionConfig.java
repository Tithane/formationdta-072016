package dta.pizzeria_spring.transaction;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaMap;

@EnableTransactionManagement
public class SpringJdbcTransactionConfig {
	@Bean
	public Stockage<?> stockage() {
		return new StockagePizzaMap();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/jpapizzeria?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
	
	@Bean
	public DataSourceTransactionManager txManager(DataSource datasource){
		return new DataSourceTransactionManager(datasource);
	}
}
