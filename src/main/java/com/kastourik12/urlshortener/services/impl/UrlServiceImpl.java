/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.exceptions.NotValidUrlException;
import com.kastourik12.urlshortener.exceptions.ResourceNotFoundException;
import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationPayload;
import com.kastourik12.urlshortener.repositories.LongUrlRepository;
import com.kastourik12.urlshortener.services.CoderService;
import com.kastourik12.urlshortener.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ok
 */

@Service
@Transactional 
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    
    private final LongUrlRepository urlRepository;
    private final CoderService coderService;
    
    

    @Override
    public String convertToShortUrl(ShortUrlCreationPayload longUrl) {
        if(isValidUrl(longUrl.getUrl()))
            throw new NotValidUrlException();

        Optional<LongUrl> optionalUrl = urlRepository.findByLongUrl(longUrl.getUrl());

        LongUrl url ;

        if(optionalUrl.isPresent()){

            url = optionalUrl.get();
            url.setShortenedTimes(url.getShortenedTimes() + 1);
            updateUrlEntity(url);

            return coderService.codeIdToShortUrl(optionalUrl.get().getId());
        }
        else {

            url = new LongUrl();
            url.setAccessedTime(0L);
            url.setShortenedTimes(1);
            url = urlRepository.save(url);

            return coderService.codeIdToShortUrl(url.getId());
        }
    }


    @Override
    public RedirectView redirectToOriginalUrl(String shortUrl) {
        Long id = coderService.decodeShortUrlToId(shortUrl);
        LongUrl url = urlRepository.findById(id)
                    .orElseThrow(
                        () -> new ResourceNotFoundException(shortUrl)
                    );

        url.setAccessedTime( url.getAccessedTime() + 1 );

        updateUrlEntity(url); // async func for updating the entity

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://" + url.getLongUrl());


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
