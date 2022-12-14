package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.exceptions.ResourceNotFoundException;
import com.kastourik12.urlshortener.models.LongUrl;

import com.kastourik12.urlshortener.models.Visit;
import com.kastourik12.urlshortener.repositories.LongUrlRepository;
import com.kastourik12.urlshortener.repositories.VisitRepository;

import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.services.AuthService;

import com.kastourik12.urlshortener.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final LongUrlRepository urlRepository;
    private final VisitRepository visitRepository;


    private final AuthService authService;

    @Override
    public List<LongUrl> getAllUrls() {
        return urlRepository.findAll();
    }


    @Override
    public List<LongUrl> getVisitedUrls() {
        User user = authService.getCurrentUser();
        return visitRepository.findVisitByUser(user).stream()
                .map(Visit::getLongUrl).toList();
    }

    @Override
    public List<Visit> getUrlVisits(Long urlId) {
        LongUrl url = urlRepository.findById(urlId).orElseThrow(() -> new ResourceNotFoundException("there is no url with this  id :" + urlId));
        return visitRepository.findVisitByLongUrl(url);
    }

    @Override
    public List<Visit> getUserUrlVisits(Long id) {
        User user = authService.getCurrentUser();
        LongUrl url = urlRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("url with the id:" + id + "doesn't exist"));
        return visitRepository.findVisitByUserAndLongUrl(user,url);
    }


}
