package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.exceptions.CustomException;
import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import com.kastourik12.urlshortener.repositories.LongUrlRepository;
import com.kastourik12.urlshortener.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor @Service
public class UrlServiceImpl implements UrlService {
    private final LongUrlRepository urlRepository;


    @Override
    public List<LongUrl> getAllUrls() {
        return urlRepository.findAll();
    }

    @Override
    public List<LongUrl> getCreatedUrls(User user) {
        return null;
    }

    @Override
    public LongUrl getUrlById(Long id) {
        return urlRepository.findById(id).orElseThrow(() -> new CustomException("no url found for this short",HttpStatus.NOT_FOUND) );
    }

    @Override
    public LongUrl saveUrlForGuest(ShortUrlCreationRequest payload) {
        Optional<LongUrl> optionalUrl = urlRepository.findByLongUrl(payload.getUrl());
        LongUrl url ;
        if(optionalUrl.isPresent()){
            url = optionalUrl.get();
            url.setShortenedTimes(url.getShortenedTimes() + 1);
            updateUrl(url);
        }
        else {
            url = new LongUrl();
            url.setVisitedTime(0L);
            url.setShortenedTimes(1);
            url.setLongUrl(payload.getUrl());
            url.setCreatedAt(new Date());
            url = urlRepository.save(url);
        }
        return urlRepository.save(url);
    }

    @Override
    public LongUrl saveUrlForLoggedInUser(ShortUrlCreationRequest payload,User user) {
        Optional<LongUrl> optionalUrl = urlRepository.findByLongUrlAndCreatedBy(payload.getUrl(),user);
        LongUrl url;
        if(optionalUrl.isPresent()){
            url = optionalUrl.get();
            url.setShortenedTimes(url.getShortenedTimes() + 1);
        }else{
            url = new LongUrl();
            url.setVisitedTime(0L);
            url.setShortenedTimes(1);
            url.setLongUrl(payload.getUrl());
            url.setCreatedAt(new Date());
            url.setCreatedBy(user);
        }
        return urlRepository.save(url);
    }

    @Override @Async
    public void updateUrl(LongUrl url){
        urlRepository.save(url);
    }
}
