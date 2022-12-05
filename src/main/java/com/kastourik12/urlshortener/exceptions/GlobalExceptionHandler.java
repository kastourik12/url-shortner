/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> exception(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> requestPayloadValidation(MethodArgumentNotValidException exception){
        return new ResponseEntity<>(exception.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidUrlException.class)
    public ResponseEntity<?> UrlIsNotValid(){
        return new ResponseEntity<>("URL should be valid",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity<?> unauthorizedException(){
        return new ResponseEntity<>("unauthorized",HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UsernameExistsException.class)
    public ResponseEntity<?> UsernameExists(){
        return new ResponseEntity<>("Username already exists",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(){
        return new ResponseEntity<>("Not Authorized", HttpStatus.FORBIDDEN);
    }

   @ExceptionHandler(value = Exception.class)
   public ResponseEntity<?> globalExceptions(Exception exception){
      log.error(exception.getClass().getName(),exception.getMessage());
       return new ResponseEntity<>("Service unavailable try later ",HttpStatus.INTERNAL_SERVER_ERROR);
   }

}
