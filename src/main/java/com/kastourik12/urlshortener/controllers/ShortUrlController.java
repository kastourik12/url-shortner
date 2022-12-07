/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.controllers;

import antlr.Token;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import com.kastourik12.urlshortener.payloads.response.ShortUrlCreationResponse;
import com.kastourik12.urlshortener.services.ShortUrlService;
import com.kastourik12.urlshortener.services.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/re")
@RequiredArgsConstructor
@Slf4j
public class ShortUrlController {

    
    private final ShortUrlService urlService;


    @PostMapping("/create")
    public ResponseEntity<ShortUrlCreationResponse> convertToShortUrl(@RequestBody @Valid ShortUrlCreationRequest longUrl){

        log.info(" Trying to create short Url for :" + longUrl.getUrl());

        return ResponseEntity.ok(urlService.convertToShortUrl(longUrl));
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getAndRedirect(@PathVariable String shortUrl, HttpServletRequest request){
        return urlService.redirectToOriginalUrl(shortUrl,request);
    }
}
