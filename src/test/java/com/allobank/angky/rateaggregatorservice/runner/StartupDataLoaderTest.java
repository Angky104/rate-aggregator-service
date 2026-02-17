package com.allobank.angky.rateaggregatorservice.runner;

import com.allobank.angky.rateaggregatorservice.client.FrankfurterApiClient;
import com.allobank.angky.rateaggregatorservice.dto.external.HistoricalRatesResponseDTO;
import com.allobank.angky.rateaggregatorservice.dto.external.LatestRatesResponseDTO;
import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartupDataLoaderTest {

    @Mock
    private FrankfurterApiClient apiClient;

    @Mock
    private InMemoryDataStore dataStore;

    @Mock
    private ApplicationArguments args;

    @InjectMocks
    private StartupDataLoader loader;

    @Test
    void shouldLoadDataOnStartup() {

        LatestRatesResponseDTO latestResponse = new LatestRatesResponseDTO();
        latestResponse.setBase("IDR");
        latestResponse.setDate("2026-02-13");
        latestResponse.setRates(
                Map.of("USD", BigDecimal.valueOf(0.000064))
        );

        when(apiClient.getLatestRates(anyString(), anyString()))
                .thenReturn(latestResponse);

        HistoricalRatesResponseDTO historicalResponse =
                new HistoricalRatesResponseDTO();
        historicalResponse.setRates(
                Map.of(
                        "2024-01-01", Map.of("USD", BigDecimal.valueOf(0.000063)),
                        "2024-01-02", Map.of("USD", BigDecimal.valueOf(0.000064))
                )
        );

        when(apiClient.getHistoricalRates(
                anyString(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(historicalResponse);

        when(apiClient.getCurrencies())
                .thenReturn(
                        Map.of(
                                "IDR", "Indonesian Rupiah",
                                "USD", "United States Dollar"
                        )
                );

        assertDoesNotThrow(() -> loader.run(args));

        verify(apiClient).getLatestRates("IDR", "USD");
        verify(apiClient).getHistoricalRates(
                "2024-01-01",
                "2024-01-05",
                "IDR",
                "USD"
        );
        verify(apiClient).getCurrencies();

        verify(dataStore).setData(anyList(), anyList(), anyList());
    }
}
