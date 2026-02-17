package com.allobank.angky.rateaggregatorservice.service.strategy;

import com.allobank.angky.rateaggregatorservice.dto.response.CurrencyDTO;
import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupportedCurrenciesFetcherTest {

    @Mock
    private InMemoryDataStore store;

    private SupportedCurrenciesFetcher fetcher;

    @BeforeEach
    void setup() {
        fetcher = new SupportedCurrenciesFetcher();
    }

    @Test
    void shouldReturnCurrenciesFromStore() {

        List<CurrencyDTO> expected = List.of(new CurrencyDTO("USD", "Dollar"));

        when(store.getCurrencies()).thenReturn(expected);

        List<CurrencyDTO> result = fetcher.getData(store);

        assertEquals(expected, result);
    }
}
