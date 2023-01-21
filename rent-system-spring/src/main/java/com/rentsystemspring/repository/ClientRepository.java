package com.rentsystemspring.repository;

import com.rentsystemspring.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByUsernameAndPassword(String username, String password);
}
