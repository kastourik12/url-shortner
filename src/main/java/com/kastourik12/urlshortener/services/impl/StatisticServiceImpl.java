package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.models.Visit;
import com.kastourik12.urlshortener.repositories.LongUrlRepository;
import com.kastourik12.urlshortener.repositories.VisitRepository;
import com.kastourik12.urlshortener.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final LongUrlRepository urlRepository;
    private final VisitRepository visitRepository;
    @Override
    public List<LongUrl> getAllUrls() {
        return urlRepository.findAll();
    }


}
