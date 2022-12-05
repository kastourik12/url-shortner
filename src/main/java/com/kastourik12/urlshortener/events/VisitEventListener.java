package com.kastourik12.urlshortener.events;

import com.kastourik12.urlshortener.models.User;
import com.kastourik12.urlshortener.models.Visit;
import com.kastourik12.urlshortener.repositories.UserRepository;
import com.kastourik12.urlshortener.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component @RequiredArgsConstructor
public class VisitEventListener {
    private final UserRepository userRepository;
    private final VisitRepository visitRepository;

    @EventListener
    @Async
    public void handleVisitEvent(VisitEvent event){
        Visit visit = new Visit();
            visit.setLongUrl(event.getLongUrl());
            visitRepository.save(visit);
        }


}


