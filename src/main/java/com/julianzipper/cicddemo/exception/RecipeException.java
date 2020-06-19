package com.julianzipper.cicddemo.exception;

@SuppressWarnings("serial")
public class RecipeException extends RuntimeException{
    public RecipeException(Long id, String message) {
        super(String.format("Recipe %s: %s", id, message));
    }
}