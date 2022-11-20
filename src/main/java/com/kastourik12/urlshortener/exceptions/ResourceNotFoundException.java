/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.exceptions;

import lombok.Getter;

/**
 *
 * @author ok
 */
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String message;
    public ResourceNotFoundException(String message){
        super(message);
        this.message = message;
    }

}
