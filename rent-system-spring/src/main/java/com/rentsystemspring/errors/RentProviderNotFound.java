package com.rentsystemspring.errors;

public class RentProviderNotFound extends RuntimeException {
    public RentProviderNotFound(Integer id){
        super("Unable to find rent provider " + id);
    }
}
