package com.allobank.angky.rateaggregatorservice.service.strategy;

import com.allobank.angky.rateaggregatorservice.dto.response.HistoricalRateDTO;
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
class HistoricalRatesFetcherTest {

    @Mock
    private InMemoryDataStore store;

    private HistoricalRatesFetcher fetcher;

    @BeforeEach
    void setup() {
        fetcher = new HistoricalRatesFetcher();
    }

    @Test
    void shouldReturnHistoricalRatesFromStore() {

        List<HistoricalRateDTO> expected = List.of(new HistoricalRateDTO());

        when(store.getHistoricalRates()).thenReturn(expected);

        List<HistoricalRateDTO> result = fetcher.getData(store);

        assertEquals(expected, result);
    }
}
