/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> exception(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> requestPayloadValidation(ConstraintViolationException exception){
        return new ResponseEntity<>(exception.getConstraintViolations(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = invalidUrlException.class)
    public ResponseEntity<?> UrlIsNotValid(){
        return new ResponseEntity<>("URL should be valid",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity<?> BadCredentials(){
        return new ResponseEntity<>("Bad Credentials",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameExistsException.class)
    public ResponseEntity<?> UsernameExists(){
        return new ResponseEntity<>("Username alreay exists",HttpStatus.BAD_REQUEST);
    }
}
