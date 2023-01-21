package com.rentsystemspring.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gym implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private double hourlyPrice;
    private String type;

    public Gym(String name, String address, double hourlyPrice, String type) {
        this.name = name;
        this.address = address;
        this.hourlyPrice = hourlyPrice;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " " + address;
    }
}
