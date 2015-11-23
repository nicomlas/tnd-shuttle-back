package com.tripndrive.shuttle.controller;

import com.tripndrive.shuttle.exception.NotFoundException;
import com.tripndrive.shuttle.exception.ThisIsBadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LG on 23/11/2015.
 */
@ControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public void notFoundError(HttpServletRequest req){
        System.out.println("error 404" + req.getRequestURL());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ThisIsBadException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {}

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public void handleError(HttpServletRequest req, Exception exc) {
        System.out.println("error 500" + req.getRequestURL());
        req.setAttribute("exc", exc);
    }
}

