package com.kastourik12.urlshortener.services;

import com.kastourik12.urlshortener.exceptions.UnAuthorizedException;
import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.payloads.request.SignInRequest;
import com.kastourik12.urlshortener.payloads.request.SignUpRequest;

public interface AuthService {

    void saveUser(SignUpRequest request);
    String authenticateAndGetJwt(SignInRequest request) ;

    User getCurrentUser();
}
