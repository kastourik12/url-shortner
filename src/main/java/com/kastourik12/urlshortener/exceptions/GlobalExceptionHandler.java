/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> requestPayloadValidation(MethodArgumentNotValidException exception){
        return new ResponseEntity<>(exception.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler(value = Exception.class)
   public ResponseEntity<?> globalExceptions(Exception exception){
      log.error(exception.getClass().getName(),exception.getMessage());
       return new ResponseEntity<>("Service unavailable try later ",HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<?> customExceptionHandling(CustomException exception){
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getMessage(),exception.getHttpStatus());
   }

}
