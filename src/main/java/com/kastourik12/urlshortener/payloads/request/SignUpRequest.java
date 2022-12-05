package com.kastourik12.urlshortener.payloads.request;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;


@Data
public class SignUpRequest {
    @NotBlank(message = "username should not be empty")
    private String username;
    @NotBlank(message = "password should not be empty")
    private String password;
    @Nullable
    private String[] roles;
}
