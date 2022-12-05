package com.kastourik12.urlshortener.utils;

import com.kastourik12.urlshortener.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {

    @Bean
    public AuditorAwareImpl auditorAware(AuthService authService){
        return new AuditorAwareImpl(authService);
    }
}
