package com.kastourik12.urlshortener.payloads.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ShortUrlCreationRequest {

    @NotBlank
    private String url;


}
