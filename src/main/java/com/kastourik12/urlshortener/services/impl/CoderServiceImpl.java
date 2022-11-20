package com.kastourik12.urlshortener.services.impl;

import com.kastourik12.urlshortener.services.CoderService;
import org.springframework.stereotype.Component;

@Component
public class CoderServiceImpl implements CoderService {
    
    //base64 {a..zA..Z1..9}
    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    
    private char[] allowedCharacters = allowedString.toCharArray();
    
    
    private int base = allowedCharacters.length;
    
    @Override
    public String codeIdToShortUrl(Long id) {
        var shortUrl = new StringBuilder();
        int reminder ;
        while(id > 0)
        {       
            reminder = id.intValue() % base;
            shortUrl.append(allowedCharacters[reminder]);
            id /= base;
        }

        return shortUrl.reverse().toString();
    }
    @Override
    public Long decodeShortUrlToId(String shortUrl){
        var characters = shortUrl.toCharArray();
        var length = characters.length;

        var decoded = 0L;

        //counter is used to avoid reversing input string
        var counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += allowedString.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;

    }


    
    

}
