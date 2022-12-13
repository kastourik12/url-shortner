package com.kastourik12.urlshortener.repositories;

import com.kastourik12.urlshortener.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryUnitTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown(){

        underTest.deleteAll();

    }

    @Test
    public void shouldExistsByUsername() {
        //given
        String cred = "admin";

        User user = new User();
        user.setUsername(cred);
        user.setPassword(cred);
        //where
        underTest.save(user);

        //then
        assertThat(underTest.findByUsername(cred))
                .isPresent();

    }

    @Test
    public void shouldNotExistsByUsername() throws Exception{

        //given
        String cred = "test";

        //where
        Optional<User> expected = underTest.findByUsername(cred);

        //then
        assertThat(expected).isEmpty();

    }




}