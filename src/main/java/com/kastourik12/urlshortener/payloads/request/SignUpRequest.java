package com.kastourik12.urlshortener.payloads.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;


@Data
public class SignUpRequest {
    @Min(value = 6,message = "username should have at least 6 characters ")
    private String username;
    @Min(value = 8,message = "password should have at 8 least characters ")
    private String password;
    @Email(message = "email should be valid")
    private String email;


    public SignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
