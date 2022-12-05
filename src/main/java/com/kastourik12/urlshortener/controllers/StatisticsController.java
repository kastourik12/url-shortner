package com.kastourik12.urlshortener.controllers;

import java.util.List;

import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.models.Visit;
import com.kastourik12.urlshortener.services.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor


public class StatisticsController {
    private final StatisticService statisticService;

    @GetMapping("/all") @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<LongUrl>> getAll(){
        return  ResponseEntity.ok(statisticService.getAllUrls());
    }

    @GetMapping("/visited") @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<List<LongUrl>> getVisitedUrls(){
        return ResponseEntity.ok(statisticService.getVisitedUrls());
    }

    @GetMapping("/{id}") @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<Visit>> getUrlVisits(@PathVariable Long id){
        return ResponseEntity.ok(statisticService.getUrlVisits(id));
    }

    @GetMapping("visits/{id}") @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<List<Visit>> getUserUrlVisits(@PathVariable Long id){
        return ResponseEntity.ok(statisticService.getUserUrlVisits(id));
    }



}
