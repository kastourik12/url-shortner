/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.controllers;

import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import com.kastourik12.urlshortener.payloads.response.RedirectionResponse;
import com.kastourik12.urlshortener.payloads.response.ShortUrlCreationResponse;
import com.kastourik12.urlshortener.services.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/re")
@RequiredArgsConstructor
@Slf4j
public class UrlShortenerController {

    
    private final UrlShortenerService urlService;


    @PostMapping("/create")
    public ResponseEntity<ShortUrlCreationResponse> convertToShortUrl(@RequestBody @Valid ShortUrlCreationRequest longUrl, HttpServletRequest request){

        log.info(" Trying to create short Url for :" + longUrl.getUrl());

        return ResponseEntity.ok(urlService.convertToShortUrl(longUrl,request));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<RedirectionResponse> getAndRedirect(@PathVariable String shortUrl, HttpServletRequest request){
        return new ResponseEntity<>(urlService.redirectToOriginalUrl(shortUrl,request), HttpStatus.OK);
    }
}
