package com.example.Unityville.exceptions;

public class NullContentException extends RuntimeException{
    public NullContentException() {
    }

    public NullContentException(String message) {
        super(message);
    }
}
