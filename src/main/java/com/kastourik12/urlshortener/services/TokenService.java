package com.kastourik12.urlshortener.services;

import com.kastourik12.urlshortener.payloads.response.TokenCreationPayload;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    TokenCreationPayload generateToken(Authentication authentication);
    Boolean isRequestContainsValidToken(HttpServletRequest request);

}
