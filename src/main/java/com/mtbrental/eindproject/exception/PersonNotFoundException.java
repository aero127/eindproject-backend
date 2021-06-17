package com.mtbrental.eindproject.exception;

public class PersonNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PersonNotFoundException(String person) {
        super("Cannot find user " + person);
    }

}
