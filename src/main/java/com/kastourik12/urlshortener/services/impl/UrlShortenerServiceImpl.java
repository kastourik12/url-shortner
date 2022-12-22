/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.events.VisitEvent;
import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import com.kastourik12.urlshortener.payloads.response.RedirectionResponse;
import com.kastourik12.urlshortener.payloads.response.ShortUrlCreationResponse;
import com.kastourik12.urlshortener.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final CoderService coderService;
    private final TokenService tokenService;
    private final UrlService urlService;
    private final ApplicationEventPublisher eventPublisher;
    private final AuthService authService;
    @Value("${client.host}")
    private String clientHost;
    


    @Override
    public ShortUrlCreationResponse convertToShortUrl(ShortUrlCreationRequest payload,HttpServletRequest request) {
        LongUrl url ;
        if(tokenService.isRequestContainsValidToken(request))
        {
            User currentUser = authService.getCurrentUser();
            url = urlService.saveUrlForLoggedInUser(payload,currentUser);
        }else
            url = urlService.saveUrlForGuest(payload);
        return ShortUrlCreationResponse
                    .builder()
                    .url(clientHost + coderService.codeIdToShortUrl(url.getId()))
                    .shortenedTimes(url.getShortenedTimes())
                    .visitedTimes(url.getVisitedTime())
                    .build();
    }


    @Override
    public RedirectionResponse redirectToOriginalUrl(String shortUrl, HttpServletRequest request) {
        Long id = coderService.decodeShortUrlToId(shortUrl);
        LongUrl url = urlService.getUrlById(id);
        url.setVisitedTime( url.getVisitedTime() + 1 );
        if(tokenService.isRequestContainsValidToken(request))
            eventPublisher.publishEvent(new VisitEvent(url));
        urlService.updateUrl(url);
        RedirectionResponse redirectionResponse = new RedirectionResponse();
        redirectionResponse.setUrl(url.getLongUrl());
        redirectionResponse.setVisitedTimes(url.getVisitedTime());
        return  redirectionResponse;
    }

}
