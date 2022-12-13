package com.kastourik12.urlshortener.payloads.response;



import java.time.Instant;

public record TokenCreationPayload(String token, Instant expiresAt) {

}
