package com.rentsystemspring.errors;

public class OrderNotFound extends RuntimeException{
    public OrderNotFound(Integer id){
        super("Unable to find order " + id);
    }
}
