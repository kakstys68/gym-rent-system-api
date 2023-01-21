package com.rentsystemspring.repository;

import com.rentsystemspring.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Integer> {

}
