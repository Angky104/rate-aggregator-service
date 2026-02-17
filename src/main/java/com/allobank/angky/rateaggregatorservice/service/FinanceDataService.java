package com.allobank.angky.rateaggregatorservice.service;

import com.allobank.angky.rateaggregatorservice.constant.ResourceType;
import com.allobank.angky.rateaggregatorservice.service.strategy.IDRDataFetcher;
import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FinanceDataService {

    private final Map<String, IDRDataFetcher> fetcherMap;
    private final InMemoryDataStore dataStore;

    public List getData(String resourceTypeRaw) {

        ResourceType resourceType = ResourceType.fromValue(resourceTypeRaw);

        IDRDataFetcher fetcher = fetcherMap.get(resourceType.getValue());

        return fetcher.getData(dataStore);
    }
}

