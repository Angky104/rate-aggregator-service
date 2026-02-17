package com.allobank.angky.rateaggregatorservice.service.strategy;

import com.allobank.angky.rateaggregatorservice.dto.response.LatestRatesDTO;
import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class LatestRatesFetcherTest {

    private LatestRatesFetcher fetcher;

    @Mock
    private InMemoryDataStore store;

    @BeforeEach
    void setup() {
        fetcher = new LatestRatesFetcher();
    }

    @Test
    void shouldReturnDataFromStore() {
        List<LatestRatesDTO> expected = List.of(new LatestRatesDTO());

        Mockito.when(store.getLatestRates()).thenReturn(expected);

        List<LatestRatesDTO> result = fetcher.getData(store);

        Assertions.assertEquals(expected, result);
    }
}
