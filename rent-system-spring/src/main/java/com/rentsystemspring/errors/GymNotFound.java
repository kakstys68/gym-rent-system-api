package com.rentsystemspring.errors;

public class GymNotFound extends RuntimeException{
    public GymNotFound(Integer id){
        super("Unable to find gym " + id);
    }
}
