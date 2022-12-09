package com.kastourik12.urlshortener.payloads.response;

import lombok.Data;

@Data
public class SignInResponse {
    String accessToken;

    public SignInResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
