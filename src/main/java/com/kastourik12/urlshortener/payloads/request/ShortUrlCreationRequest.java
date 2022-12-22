package com.kastourik12.urlshortener.payloads.request;

import com.kastourik12.urlshortener.utils.validators.URL;
import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ShortUrlCreationRequest {

    @URL
    private String url;

}
