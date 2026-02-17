package com.allobank.angky.rateaggregatorservice.service.strategy;

import com.allobank.angky.rateaggregatorservice.dto.response.LatestRatesDTO;
import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("latest_idr_rates")
public class LatestRatesFetcher implements IDRDataFetcher {

    @Override
    public List<LatestRatesDTO> getData(InMemoryDataStore dataStore) {
        return dataStore.getLatestRates();
    }
}
