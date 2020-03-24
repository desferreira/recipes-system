package com.diego.recipes.repositories.exception;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String id){
        super("Resource not found for id "+id);
    }
}
