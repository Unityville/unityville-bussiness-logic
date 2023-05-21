package com.example.Unityville.exceptions;

public class AlreadyInsertException extends RuntimeException {
    public AlreadyInsertException() {
    }

    public AlreadyInsertException(String message) {
        super(message);
    }
}
