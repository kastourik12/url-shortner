package com.kastourik12.urlshortener.payloads.response;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ShortUrlCreationResponse {
    private String url;
    private Long visitedTimes;
    private Integer shortenedTimes;
}
