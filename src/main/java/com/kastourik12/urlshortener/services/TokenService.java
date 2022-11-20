package com.kastourik12.urlshortener.services;

import org.springframework.security.core.Authentication;

public interface TokenService {
    public String generateToken(Authentication authentication);
}
