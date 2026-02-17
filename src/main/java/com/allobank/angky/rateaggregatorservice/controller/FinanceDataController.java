package com.allobank.angky.rateaggregatorservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class FinanceDataController {

    @GetMapping("/{resourceType}")
    public ResponseEntity<String> getFinanceData(@PathVariable String resourceType) {
        // TODO do hit service
        return ResponseEntity.ok("Okay");
    }

}
