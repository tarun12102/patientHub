package com.straumann.patientHub.exception;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException(String resourceName, int fieldValue){
        super(String.format("resource %s with id %s not found ",resourceName,fieldValue));
    }
}
