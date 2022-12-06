package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.exceptions.ResourceNotFoundException;
import com.kastourik12.urlshortener.exceptions.UnAuthorizedException;
import com.kastourik12.urlshortener.exceptions.UsernameExistsException;
import com.kastourik12.urlshortener.models.ERole;
import com.kastourik12.urlshortener.models.Role;
import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.payloads.request.SignInRequest;
import com.kastourik12.urlshortener.payloads.request.SignUpRequest;
import com.kastourik12.urlshortener.repositories.RoleRepository;
import com.kastourik12.urlshortener.repositories.UserRepository;
import com.kastourik12.urlshortener.services.AuthService;
import com.kastourik12.urlshortener.services.TokenService;
import com.kastourik12.urlshortener.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @Override
    public void saveUser(SignUpRequest request) {
            userService.saveUser(request);
    }

    @Override
    public String authenticateAndGetJwt(SignInRequest request) {

        try{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        return tokenService.generateToken(authentication);
        }
        catch (RuntimeException e){
            throw new UnAuthorizedException();
        }

    }

    @Override
    public User getCurrentUser() {

        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userService.getUserByUsername(username);

        }catch (RuntimeException exception){
            throw new UnAuthorizedException();
        }

    }

}
