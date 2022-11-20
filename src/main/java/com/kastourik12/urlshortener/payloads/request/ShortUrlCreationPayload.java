package com.kastourik12.urlshortener.payloads.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @NoArgsConstructor @Setter
public class ShortUrlCreationPayload {
    @NotBlank
    private String url;
}
