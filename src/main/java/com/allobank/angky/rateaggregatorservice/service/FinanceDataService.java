package com.allobank.angky.rateaggregatorservice.service;

import com.allobank.angky.rateaggregatorservice.constant.ResourceType;
import com.allobank.angky.rateaggregatorservice.service.strategy.IDRDataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FinanceDataService {

    private final Map<String, IDRDataFetcher> fetcherMap;

    public List getData(String resourceTypeRaw) {

        ResourceType resourceType = ResourceType.fromValue(resourceTypeRaw);

        // TODO : get fetcher map resource type
        // TODO : return from inMemory data store

        return new ArrayList<>();
    }
}

