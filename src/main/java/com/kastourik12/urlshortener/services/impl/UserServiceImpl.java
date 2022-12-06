package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.exceptions.ResourceNotFoundException;
import com.kastourik12.urlshortener.exceptions.UsernameExistsException;
import com.kastourik12.urlshortener.models.ERole;
import com.kastourik12.urlshortener.models.Role;
import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.payloads.request.SignUpRequest;
import com.kastourik12.urlshortener.repositories.RoleRepository;
import com.kastourik12.urlshortener.repositories.UserRepository;
import com.kastourik12.urlshortener.services.UserService;
import lombok.RequiredArgsConstructor;
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
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(""));
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
