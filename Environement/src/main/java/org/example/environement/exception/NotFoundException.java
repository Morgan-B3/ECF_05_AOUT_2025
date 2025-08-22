package org.example.environement.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("Not found");
    }
}
