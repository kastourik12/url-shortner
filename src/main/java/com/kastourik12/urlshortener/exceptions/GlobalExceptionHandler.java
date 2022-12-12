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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> requestPayloadValidation(MethodArgumentNotValidException exception){
        return new ResponseEntity<>(exception.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<?> customExceptionHandling(CustomException exception){
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getMessage(),exception.getHttpStatus());
   }

}
