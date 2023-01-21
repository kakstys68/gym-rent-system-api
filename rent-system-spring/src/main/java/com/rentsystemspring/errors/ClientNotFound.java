package com.rentsystemspring.errors;

public class ClientNotFound extends RuntimeException{
    public ClientNotFound(Integer id){
        super("Unable to find client " + id);
    }
}
