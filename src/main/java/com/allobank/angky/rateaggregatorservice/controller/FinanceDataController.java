package com.allobank.angky.rateaggregatorservice.controller;

import com.allobank.angky.rateaggregatorservice.service.FinanceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class FinanceDataController {

    private final FinanceDataService financeDataService;

    @GetMapping("/{resourceType}")
    public ResponseEntity<List<?>> getFinanceData(@PathVariable String resourceType) {
        List<?> response = financeDataService.getData(resourceType);
        return ResponseEntity.ok(response);
    }

}
