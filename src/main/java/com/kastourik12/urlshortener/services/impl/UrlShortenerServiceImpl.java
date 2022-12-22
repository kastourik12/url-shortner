/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.events.VisitEvent;
import com.kastourik12.urlshortener.exceptions.CustomException;
import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import com.kastourik12.urlshortener.payloads.response.RedirectionResponse;
import com.kastourik12.urlshortener.payloads.response.ShortUrlCreationResponse;
import com.kastourik12.urlshortener.repositories.LongUrlRepository;
import com.kastourik12.urlshortener.services.CoderService;
import com.kastourik12.urlshortener.services.ShortUrlService;
import com.kastourik12.urlshortener.services.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URL;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author ok
 */

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ShortUrlServiceImpl implements ShortUrlService {
    
    private final LongUrlRepository urlRepository;
    private final CoderService coderService;
    private final TokenService tokenService;
    private final ApplicationEventPublisher eventPublisher;

    @Value("${client.host}")
    private String clientHost;
    
    

    @Override
    public ShortUrlCreationResponse convertToShortUrl(ShortUrlCreationRequest payload) {

        if(isNotValidUrl(payload.getUrl()))
        {
            String[] payloadUrlStrings = payload.getUrl().split("\\.");
            if( payloadUrlStrings.length  > 1 && !payloadUrlStrings[0].isEmpty() && !payloadUrlStrings[1].isEmpty() )
                payload.setUrl("https://" + payload.getUrl());
            if (isNotValidUrl(payload.getUrl()))
                throw new CustomException("Url should be valid", HttpStatus.BAD_REQUEST);
        }


        Optional<LongUrl> optionalUrl = urlRepository.findByLongUrl(payload.getUrl());
        LongUrl url ;

        if(optionalUrl.isPresent()){
                url = optionalUrl.get();
                url.setShortenedTimes(url.getShortenedTimes() + 1);
                updateUrlEntity(url);
            }
        else {

                url = new LongUrl();
                url.setVisitedTime(0L);
                url.setShortenedTimes(1);
                url.setLongUrl(payload.getUrl());
                url.setCreatedAt(new Date());
                url = urlRepository.save(url);
            }

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
        LongUrl url = urlRepository.findById(id)
                    .orElseThrow(
                        () -> new CustomException("no url found for this short",HttpStatus.NOT_FOUND)
                    );
        url.setVisitedTime( url.getVisitedTime() + 1 );
        updateUrlEntity(url); // async func for updating url entity

        if(tokenService.isRequestContainsValidToken(request))
            eventPublisher.publishEvent(new VisitEvent(url));

        RedirectionResponse redirectionResponse = new RedirectionResponse();
        redirectionResponse.setUrl(url.getLongUrl());
        redirectionResponse.setVisitedTimes(url.getVisitedTime());

        return  redirectionResponse;

    }

    @Async
    void updateUrlEntity(LongUrl url){
        urlRepository.save(url);
    }



    private boolean isNotValidUrl(String url)
    {
        /* Try creating a valid URL */
        try {

            new URL(url).toURI();

            return false;

        }
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {

            return true;
        }
    }


}
