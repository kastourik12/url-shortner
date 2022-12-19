package com.kastourik12.urlshortener.payloads.response;

import lombok.Data;

@Data
public class RedirectionResponse {
    private String url;
    private Long visitedTimes;
}
