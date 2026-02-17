package com.allobank.angky.rateaggregatorservice.service.strategy;

import com.allobank.angky.rateaggregatorservice.dto.response.HistoricalRateDTO;
import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("historical_idr_usd")
public class HistoricalRatesFetcher implements IDRDataFetcher {

    @Override
    public List<HistoricalRateDTO> getData(InMemoryDataStore dataStore) {
        return dataStore.getHistoricalRates();
    }
}
