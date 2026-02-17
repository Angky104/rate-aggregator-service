package com.allobank.angky.rateaggregatorservice.service.strategy;

import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;

import java.util.List;

public interface IDRDataFetcher<T> {
    List<T> getData(InMemoryDataStore store);
}
