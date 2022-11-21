package com.kastourik12.urlshortener.services;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    String generateToken(Authentication authentication);
    Boolean isRequestContainsValidToken(HttpServletRequest request);

}
