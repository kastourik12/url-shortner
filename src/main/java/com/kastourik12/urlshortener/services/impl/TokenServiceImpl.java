package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.services.TokenService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    private final BearerTokenResolver tokenResolver;


    @Override
    public String generateToken(Authentication authentication){
        Instant now =Instant.now();
        String scope = authentication.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope",scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public Boolean isRequestContainsValidToken(HttpServletRequest request) {
        try {
            String token =tokenResolver.resolve(request);
            return jwtDecoder.decode(token).getExpiresAt().isAfter(Instant.now());
        }
        catch (Exception e){
            return false;
        }
    }


}
