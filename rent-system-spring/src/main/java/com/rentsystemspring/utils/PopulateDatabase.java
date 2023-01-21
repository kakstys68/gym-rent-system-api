package com.rentsystemspring.utils;

import com.rentsystemspring.RentSystemSpringApplication;
import com.rentsystemspring.model.Client;
import com.rentsystemspring.model.User;
import com.rentsystemspring.repository.ClientRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;

import java.time.LocalDate;

@Configuration
public class PopulateDatabase {
/*
    private static final Logger logger = LoggerFactory.getLogger(RentSystemSpringApplication.class);
    @Bean
    CommandLineRunner insertObj(ClientRepository rep){
        return args -> {
            rep.save(new Client("test", "test", "test", "test", "test@mail.com", LocalDate.parse("2001-10-10")));
            rep.save(new Client("test1", "test1", "test1", "test1", "test1@mail.com", LocalDate.parse("2002-10-10")));
            rep.save(new Client("test2", "test2", "test2", "test2", "test2@mail.com", LocalDate.parse("2003-10-10")));
            logger.info("All users found with findAll():");
            logger.info("-------------------------------");
            for (User client : rep.findAll()) {
                logger.info(client.toString());
            }
            logger.info("");

        };
    }

 */
}
