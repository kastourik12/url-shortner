package com.kastourik12.urlshortener.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
public class SignUpRequest {
    @NotBlank(message = "username should not be empty")
    private String username;
    @NotBlank(message = "password should not be empty")
    private String password;
    @Nullable
    private String[] roles;

    public SignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
