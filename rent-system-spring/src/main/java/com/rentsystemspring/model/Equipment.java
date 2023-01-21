package com.rentsystemspring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String sportType;
    private int quantity;



    //@ManyToOne
    //private Gym gym;

    public Equipment(String name, String sportType, int quantity) {
        this.name = name;
        this.sportType = sportType;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " " + sportType;
    }

}
