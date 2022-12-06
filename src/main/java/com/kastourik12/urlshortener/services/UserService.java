package com.kastourik12.urlshortener.services;


import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.payloads.request.SignUpRequest;

public interface UserService {

    void saveUser(SignUpRequest signUpRequest);

    User getUserByUsername(String Username);

}
