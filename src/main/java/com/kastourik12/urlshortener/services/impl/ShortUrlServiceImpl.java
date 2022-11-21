/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.exceptions.invalidUrlException;
import com.kastourik12.urlshortener.exceptions.ResourceNotFoundException;
import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import com.kastourik12.urlshortener.payloads.response.ShortUrlCreationResponse;
import com.kastourik12.urlshortener.repositories.LongUrlRepository;
import com.kastourik12.urlshortener.services.CoderService;
import com.kastourik12.urlshortener.services.ShortUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.net.URL;
import java.util.Date;
import java.util.List;
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
    
    

    @Override
    public ShortUrlCreationResponse convertToShortUrl(ShortUrlCreationRequest request) {

        if(!isValidUrl(request.getUrl()))
        {
            String[] u = request.getUrl().split("\\.");

            if( u.length  > 1 && !u[0].isEmpty() && !u[1].isEmpty() )

                request.setUrl("https://" + request.getUrl());

            if (!isValidUrl(request.getUrl()))
                throw new invalidUrlException();
        }



        Optional<LongUrl> optionalUrl = urlRepository.findByLongUrl(request.getUrl());

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
                url.setLongUrl(request.getUrl());
                url.setCreatedAt(new Date());
                url = urlRepository.save(url);
                url.setShortUrl(coderService.codeIdToShortUrl(url.getId()));
                updateUrlEntity(url);

            }

        return ShortUrlCreationResponse
                    .builder()
                    .url("http://localhost:8082/re/" + url.getShortUrl())
                    .shortenedTimes(url.getShortenedTimes())
                    .visitedTimes(url.getVisitedTime())
                    .build();



    }


    @Override
    public RedirectView redirectToOriginalUrl(String shortUrl) {
        Long id = coderService.decodeShortUrlToId(shortUrl);
        LongUrl url = urlRepository.findById(id)
                    .orElseThrow(
                        () -> new ResourceNotFoundException(shortUrl)
                    );

        url.setVisitedTime( url.getVisitedTime() + 1 );

        updateUrlEntity(url); // async func for updating the entity
        log.info(url.getLongUrl());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url.getLongUrl());


        return  redirectView;
    }

    @Override
    public List<LongUrl> getAllUrls() {
        return urlRepository.findAll();
    }

    @Async
    void updateUrlEntity(LongUrl url){
        urlRepository.save(url);
    }

    private boolean isValidUrl(String url)
    {
        /* Try creating a valid URL */
        try {

            new URL(url).toURI();

            return true;

        }
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {

            return false;
        }
    }


}
