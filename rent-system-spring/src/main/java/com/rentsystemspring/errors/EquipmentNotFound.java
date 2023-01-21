package com.rentsystemspring.errors;

public class EquipmentNotFound extends RuntimeException{
    public EquipmentNotFound(Integer id){
        super("Unable to find equipment " + id);
    }
}
