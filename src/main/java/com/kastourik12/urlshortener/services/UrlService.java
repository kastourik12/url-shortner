package com.kastourik12.urlshortener.services;

import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;

import java.util.List;

public interface UrlService {
    List<LongUrl> getAllUrls();
    List<LongUrl> getCreatedUrls(User user);

    LongUrl getUrlById(Long id);

    LongUrl saveUrlForGuest(ShortUrlCreationRequest payload);
    LongUrl saveUrlForLoggedInUser(ShortUrlCreationRequest payload,User user);
    void updateUrl(LongUrl url);

}
