/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.services;


import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import com.kastourik12.urlshortener.payloads.response.ShortUrlCreationResponse;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

/**
 *
 * @author ok
 */

public interface ShortUrlService {

    ShortUrlCreationResponse convertToShortUrl(ShortUrlCreationRequest longUrl);

    RedirectView redirectToOriginalUrl(String shortUrl);

    List<LongUrl> getAllUrls();
}
