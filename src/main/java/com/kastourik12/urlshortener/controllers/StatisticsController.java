package com.kastourik12.urlshortener.controllers;

import java.util.List;

import com.kastourik12.urlshortener.models.LongUrl;
import com.kastourik12.urlshortener.services.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
public class StatisticsController {
    private final StatisticService statisticService;

    @GetMapping("/all")
    public ResponseEntity<List<LongUrl>> getAll(){

        return  ResponseEntity.ok(statisticService.getAllUrls());
    }


}
