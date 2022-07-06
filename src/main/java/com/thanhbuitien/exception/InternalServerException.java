package com.thanhbuitien.exception;

public class InternalServerException extends RuntimeException{

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException() {
    }
}
