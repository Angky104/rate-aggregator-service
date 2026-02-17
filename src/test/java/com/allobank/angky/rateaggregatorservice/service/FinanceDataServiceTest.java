package com.allobank.angky.rateaggregatorservice.service;

import com.allobank.angky.rateaggregatorservice.dto.response.LatestRatesDTO;
import com.allobank.angky.rateaggregatorservice.exception.InvalidResourceTypeException;
import com.allobank.angky.rateaggregatorservice.service.strategy.IDRDataFetcher;
import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class FinanceDataServiceTest {

    @Mock
    private InMemoryDataStore dataStore;

    @Mock
    private IDRDataFetcher latestFetcher;

    private FinanceDataService service;

    @BeforeEach
    void setup() {
        Map<String, IDRDataFetcher> map = Map.of(
                "latest_idr_rates", latestFetcher
        );

        service = new FinanceDataService(map, dataStore);
    }

    @Test
    void shouldReturnLatestRates() {
        List<LatestRatesDTO> expected = List.of(new LatestRatesDTO());

        Mockito.when(latestFetcher.getData(dataStore)).thenReturn(expected);

        List<?> result = service.getData("latest_idr_rates");

        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldThrowExceptionForInvalidResourceType() {
        Assertions.assertThrows(
                InvalidResourceTypeException.class,
                () -> service.getData("wrong_type")
        );
    }
}
