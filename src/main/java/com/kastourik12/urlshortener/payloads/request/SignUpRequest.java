package com.kastourik12.urlshortener.payloads.request;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;


@Data
public class SignUpRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Nullable
    private String[] roles;
}
