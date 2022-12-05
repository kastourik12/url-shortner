package com.kastourik12.urlshortener.utils;

import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<User>{
    private final AuthService authService;



    @Override
    public Optional<User> getCurrentAuditor() {
        try{
            User user = authService.getCurrentUser();
            return Optional.of(user);
        }catch (RuntimeException ignored){
            return Optional.empty();
        }
    }
}
