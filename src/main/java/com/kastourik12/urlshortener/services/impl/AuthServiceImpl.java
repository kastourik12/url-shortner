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
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @Override
    public void saveUser(SignUpRequest request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent())
            throw new UsernameExistsException();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        if(request.getRoles() == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found in the database"));
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
        }else{
            user.setRoles(getRoles(request.getRoles()));
        }
        userRepository.save(user);
    }

    @Override
    public String authenticateAndGetJwt(SignInRequest request) {

        try{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );

        return tokenService.generateToken(authentication);

        }

        catch (Exception e){
            throw new UnAuthorizedException();
        }

    }

    @Override
    public User getCurrentUser() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByUsername(username).orElseThrow(UnAuthorizedException::new);

        }catch (Exception exception){
            throw new UnAuthorizedException();
        }

    }


    private Set<Role> getRoles(String [] roles){

        Set<Role> userRoles = new HashSet<>();
        for(String role : roles) {
            userRoles.add(roleRepository.findByName(Enum.valueOf(ERole.class,role))
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found in the database"))
            );
        }
        return userRoles;
    }
}
