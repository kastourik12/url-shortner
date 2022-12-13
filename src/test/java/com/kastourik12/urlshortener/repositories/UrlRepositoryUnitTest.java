package com.kastourik12.urlshortener.repositories;

import com.kastourik12.urlshortener.models.LongUrl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UrlRepositoryUnitTest {
    @Autowired
    LongUrlRepository underTest;

    @Test
    public void itShouldExistByLongUrl() {


        //given

        final String url = "www.google.com";
        //when
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



