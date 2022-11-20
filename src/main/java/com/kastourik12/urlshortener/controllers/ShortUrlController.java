/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.controllers;

import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationPayload;
import com.kastourik12.urlshortener.services.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 *
 * @author ok
 */
@RestController("short/")
@RequiredArgsConstructor
@Slf4j
public class ShortUrlController {

    
    private final UrlService urlService;


    @PostMapping("/create")
    public ResponseEntity<String> convertToShortUrl(@RequestBody @Valid ShortUrlCreationPayload longUrl){
        log.info(" Trying to create short Url for :" + longUrl.getUrl());

        return ResponseEntity.ok("http://localhost:8082/"+urlService.convertToShortUrl(longUrl));
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getAndRedirect(@PathVariable String shortUrl, HttpServletRequest request){
        return urlService.redirectToOriginalUrl(shortUrl);
    }
}
