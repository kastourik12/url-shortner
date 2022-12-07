package com.kastourik12.urlshortener.events;

import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.models.Visit;
import com.kastourik12.urlshortener.repositories.LongUrlRepository;
import com.kastourik12.urlshortener.repositories.UserRepository;
import com.kastourik12.urlshortener.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component @RequiredArgsConstructor
public class VisitEventListener {
    private final VisitRepository visitRepository;

    @EventListener
    public void handleVisitEvent(VisitEvent event){
            Visit visit = new Visit();
            LongUrl url = event.getLongUrl();
            visit.setLongUrl(url);
            visitRepository.save(visit);
    }


}


