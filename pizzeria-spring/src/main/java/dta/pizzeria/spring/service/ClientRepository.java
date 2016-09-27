package dta.pizzeria.spring.service;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer > {

}
