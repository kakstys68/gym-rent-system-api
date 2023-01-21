package com.rentsystemspring.errors;

public class CommentNotFound extends RuntimeException {
    public CommentNotFound(Integer id){
        super("Unable to find comment " + id);
    }
}
