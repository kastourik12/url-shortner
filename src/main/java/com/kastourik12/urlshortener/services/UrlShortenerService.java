/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.services;


import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import com.kastourik12.urlshortener.payloads.response.RedirectionResponse;
import com.kastourik12.urlshortener.payloads.response.ShortUrlCreationResponse;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ok
 */

public interface UrlShortenerService {

    ShortUrlCreationResponse convertToShortUrl(ShortUrlCreationRequest longUrl,HttpServletRequest request);

    RedirectionResponse redirectToOriginalUrl(String shortUrl, HttpServletRequest request);

}
