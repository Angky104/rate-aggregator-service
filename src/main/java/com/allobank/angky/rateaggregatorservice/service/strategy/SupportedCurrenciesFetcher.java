package com.allobank.angky.rateaggregatorservice.service.strategy;

import com.allobank.angky.rateaggregatorservice.dto.response.CurrencyDTO;
import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("supported_currencies")
public class SupportedCurrenciesFetcher implements IDRDataFetcher {

    @Override
    public List<CurrencyDTO> getData(InMemoryDataStore dataStore) {
        return dataStore.getCurrencies();
    }
}
