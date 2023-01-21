package com.rentsystemspring.repository;

import com.rentsystemspring.model.Client;
import com.rentsystemspring.model.RentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentProviderRepository extends JpaRepository<RentProvider, Integer> {
    RentProvider findRentProviderByUsernameAndPassword(String username, String password);
}
