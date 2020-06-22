package com.julianzipper.cicddemo.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id, String type) {
        super(String.format("%s with ID %s: Not found", type, id));
    }
}