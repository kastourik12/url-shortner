package com.kastourik12.urlshortener.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {

    public static  String objectToJson(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
