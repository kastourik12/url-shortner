package com.kastourik12.urlshortener.payloads.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ShortUrlCreationRequest {

    @NotBlank(message = "url should be not empty")
    private String url;


}
