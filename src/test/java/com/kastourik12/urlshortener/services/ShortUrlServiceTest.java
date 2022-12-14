package com.kastourik12.urlshortener.services;

import com.kastourik12.urlshortener.exceptions.InvalidUrlException;
import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.payloads.request.ShortUrlCreationRequest;
import com.kastourik12.urlshortener.repositories.LongUrlRepository;
import com.kastourik12.urlshortener.services.impl.ShortUrlServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShortUrlServiceTest {

    @Mock
    private LongUrlRepository urlRepository;
    @Mock
    private CoderService coderService;
    @InjectMocks
    private ShortUrlServiceImpl underTest;

    @Test
    public void shouldThrowExceptionWhenUrlIsInvalid(){

        //given

        String invalidUrl = "exmaple";

        //where

        ShortUrlCreationRequest request = new ShortUrlCreationRequest(invalidUrl);

        //then

        assertThrows(InvalidUrlException.class,() -> {
            underTest.convertToShortUrl(request);
        });

    }

    @Test
    public void shouldReturnShortUrl() throws Exception{

        //given
        String url = "www.example.com";
        ShortUrlCreationRequest request = new ShortUrlCreationRequest(url);
        Long id = 1L;
        LongUrl urlEntity = new LongUrl();
        urlEntity.setId(id);
        urlEntity.setLongUrl(url);
        urlEntity.setShortenedTimes(0);
        urlEntity.setVisitedTime(0L);

        //where
        when(urlRepository.findByLongUrl(any())).thenReturn(Optional.of(urlEntity));
        underTest.convertToShortUrl(request);

        //then
        verify(coderService).codeIdToShortUrl(id);

    }





}
