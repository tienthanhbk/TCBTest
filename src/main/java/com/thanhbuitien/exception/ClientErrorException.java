package com.thanhbuitien.exception;

public class ClientErrorException extends RuntimeException{
    public ClientErrorException() {
    }

    public ClientErrorException(String message) {
        super(message);
    }
}
