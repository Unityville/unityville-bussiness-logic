package com.example.Unityville.controller_advice;

import com.example.Unityville.exceptions.AlreadyInsertException;
import com.example.Unityville.exceptions.NotFoundException;
import com.example.Unityville.exceptions.NullArgumentsException;
import com.example.Unityville.exceptions.NullContentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandling {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullContentException.class)
    public void handlerErrorIllegalArg(){
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullArgumentsException.class)
    public void handlerErrorNullArg(){
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyInsertException.class)
    public void handlerAlreadyInserted(){
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handlerNotFound(){
    }
}
