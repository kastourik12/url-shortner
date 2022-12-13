package com.kastourik12.urlshortener.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data @AllArgsConstructor
public class SignInResponse {
    String accessToken;
    String username;
    Instant expiresAt;
}
