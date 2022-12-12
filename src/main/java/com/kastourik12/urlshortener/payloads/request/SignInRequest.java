package com.kastourik12.urlshortener.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data @AllArgsConstructor
public class SignInRequest {

    @NotBlank(message = "username should not be empty")
    private String username;

    @NotBlank(message = "password should not be empty")
    private String password;

}
