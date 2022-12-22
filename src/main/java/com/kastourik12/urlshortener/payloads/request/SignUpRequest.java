package com.kastourik12.urlshortener.payloads.request;

import com.kastourik12.urlshortener.utils.validators.Password;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
public class SignUpRequest {
    @Size(min = 8,max = 20,message = "username must be between 8 and 20 characters")
    private String username;
    @Password
    private String password;
    @Email(message = "you must enter a valid Email")
    private String email;


    public SignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
