package com.kastourik12.urlshortener.repositories;

import com.kastourik12.urlshortener.models.LongUrl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UrlRepositoryUnitTest {
    @Autowired
    LongUrlRepository underTest;

    @Test
    public void itShouldExistByLongUrl() {


        //given

        final String url = "www.example.com";
        LongUrl urlEntity = new LongUrl();
        urlEntity.setLongUrl("www.example.com");
        //when
        underTest.save(urlEntity);
        Optional<LongUrl> expected = underTest.findByLongUrl(url);
        //then
        assertThat(expected)
                .isPresent();

    }

    @AfterEach
    public void tearDown() {
        underTest.deleteAll();
    }

    @Test
    public void itShouldNotExistByLongUrl() {
        //given
        final String url = "www.example.com";

        //when
        Optional<LongUrl> expected = underTest.findByLongUrl(url);

        //then
        assertThat(expected)
                .isEmpty();
    }
}



