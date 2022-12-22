package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.exceptions.CustomException;
import com.kastourik12.urlshortener.models.ERole;
import com.kastourik12.urlshortener.models.Role;
import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.payloads.request.SignUpRequest;
import com.kastourik12.urlshortener.repositories.RoleRepository;
import com.kastourik12.urlshortener.repositories.UserRepository;
import com.kastourik12.urlshortener.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service @RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public void saveUser(SignUpRequest request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent())
            throw new CustomException("username already exists", HttpStatus.BAD_REQUEST);
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                            .orElseThrow(
                                                    () ->
                                                            new CustomException("Role not found in the database",HttpStatus.NOT_FOUND));
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new CustomException("user with username:" + username ,HttpStatus.NOT_FOUND));
    }

}
