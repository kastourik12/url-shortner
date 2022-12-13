package com.kastourik12.urlshortener.services;

import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.payloads.request.SignInRequest;
import com.kastourik12.urlshortener.payloads.request.SignUpRequest;
import com.kastourik12.urlshortener.payloads.response.SignInResponse;

public interface AuthService {

    void saveUser(SignUpRequest request);
    SignInResponse authenticateAndGetJwt(SignInRequest request) ;

    User getCurrentUser();
}
