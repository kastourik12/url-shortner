package com.kastourik12.urlshortener.services;

import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.models.Visit;

import java.util.List;
public interface StatisticService {
    List<LongUrl> getAllUrls();

    List<LongUrl> getVisitedUrls();

    List<Visit> getUrlVisits(Long urlId);
}
