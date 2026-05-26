package com.stalinbabu.backend.exception;

public class EmailAlreadyExistsException
extends RuntimeException {

    public EmailAlreadyExistsException(
            String message
    ) {
        super(message);
    }

}