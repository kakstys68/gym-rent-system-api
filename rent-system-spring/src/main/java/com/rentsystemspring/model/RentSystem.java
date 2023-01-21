package com.rentsystemspring.model;

import java.io.Serializable;
import java.util.ArrayList;

public class RentSystem implements Serializable {
    private ArrayList<User> allUsers;
    private ArrayList<Orders> allOrders;
    private ArrayList<Gym> allGyms;

    public RentSystem() {
        this.allUsers = new ArrayList<>();
        this.allOrders = new ArrayList<>();
        this.allGyms = new ArrayList<>();
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public ArrayList<Orders> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(ArrayList<Orders> allOrders) {
        this.allOrders = allOrders;
    }

    public ArrayList<Gym> getAllGyms() {
        return allGyms;
    }

    public void setAllGyms(ArrayList<Gym> allGyms) {
        this.allGyms = allGyms;
    }
}
