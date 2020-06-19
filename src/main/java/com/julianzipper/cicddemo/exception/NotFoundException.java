package com.julianzipper.cicddemo.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id, String type) {
        super(String.format("%s %s: Not found"));
    }
}