package com.kastourik12.urlshortener.repositories;

import java.util.List;

import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
    List<Visit> findVisitByCreatedBy(User user);
    List<Visit> findVisitByLongUrl(LongUrl longUrl);
    List<Visit> findVisitByCreatedByAndLongUrl(User user, LongUrl longUrl);
}
