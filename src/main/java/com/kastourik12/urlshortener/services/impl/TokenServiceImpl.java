package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.payloads.response.TokenCreationPayload;
import com.kastourik12.urlshortener.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;

    private final BearerTokenResolver tokenResolver;


    @Override
    public TokenCreationPayload generateToken(Authentication authentication){
        Instant now =Instant.now();
        Instant expiringTime = now.plus(1, ChronoUnit.HOURS);
        String scope = authentication.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiringTime)
                .subject(authentication.getName())
                .claim("scope",scope)
                .build();
        String token =  this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenCreationPayload(token,expiringTime);
    }

    @Override
    public Boolean isRequestContainsValidToken(HttpServletRequest request) {
        try {
            tokenResolver.resolve(request);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


}
