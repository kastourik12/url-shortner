/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kastourik12.urlshortener.repositories;

import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author ok
 */
@Repository
public interface LongUrlRepository extends JpaRepository<LongUrl, Long>{

    @Query("select x from LongUrl x where x.longUrl = ?1 and x.createdBy is null ")
    Optional<LongUrl> findByLongUrl(String longUrl);
    Optional<LongUrl> findByLongUrlAndCreatedBy(String longUrl, User user);
}
