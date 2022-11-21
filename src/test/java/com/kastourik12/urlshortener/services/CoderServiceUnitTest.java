package com.kastourik12.urlshortener.services;

import com.kastourik12.urlshortener.services.impl.CoderServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CoderServiceUnitTest {

    @InjectMocks
    private CoderServiceImpl underTest;

    @Test
    @Order(1)
    public void ShouldCodeIdToBase62() throws Exception{

        //given
        Long id = 125L; // 125 to base62 is cb


        //where

        String shortUrl = underTest.codeIdToShortUrl(id);

        //then

        assertThat(shortUrl)
                .isEqualTo("cb");

    }

    @Test
    @Order(2)
    public void shouldDecodeShortUrlToIdTest() throws Exception  {

        //given
        String shortUrl = "cb"; // cb base62 to base10 is 125

        //where
        Long id = underTest.decodeShortUrlToId(shortUrl);

        //then
        assertThat(id)
                .isEqualTo(125L);

    }


}
