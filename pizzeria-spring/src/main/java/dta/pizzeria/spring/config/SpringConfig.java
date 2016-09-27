package dta.pizzeria.spring.config;



import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import dta.pizzeria.spring.service.ServiceClient;
import fr.pizzeria.service.Stockage;
import fr.pizzeria.service.StockagePizzaMap;

@Configuration
@EnableWebMvc
@ComponentScan({ "dta.pizzeria.spring.controller" })
@EnableJpaRepositories("dta.pizzeria.spring.service")
@EnableAspectJAutoProxy
public class SpringConfig {
	/*
	@Bean
	public Stockage<?> stockageP() {
		return new StockagePizzaMap();
	}
	@Bean Stockage<?> stockageC(){
		return new ServiceClient(dataSource());
	}
	*/
	/*
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pizzadb?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
    */
	
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
		emf.setPersistenceUnitName("pizzeria-unit");
		return emf;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
}
