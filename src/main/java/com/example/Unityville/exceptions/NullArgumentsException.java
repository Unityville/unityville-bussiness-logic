package com.example.Unityville.exceptions;

public class NullArgumentsException extends RuntimeException{
    public NullArgumentsException() {
    }

    public NullArgumentsException(String message) {
        super(message);
    }
}
