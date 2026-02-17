package com.allobank.angky.rateaggregatorservice.client;

import com.allobank.angky.rateaggregatorservice.config.FrankfurterProperties;
import com.allobank.angky.rateaggregatorservice.dto.external.LatestRatesResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class FrankfurterApiClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private FrankfurterProperties props;

    private FrankfurterApiClient client;

    @BeforeEach
    void setup() {
        client = new FrankfurterApiClient(restTemplate, props);
    }

    @Test
    void shouldCallLatestRatesAPI() {

        FrankfurterProperties.Endpoints endpoints =
                new FrankfurterProperties.Endpoints();
        endpoints.setLatest("/latest");

        Mockito.when(props.getEndpoints()).thenReturn(endpoints);

        Mockito.when(props.buildUrl("/latest"))
                .thenReturn("https://api.frankfurter.dev/v1/latest");

        LatestRatesResponseDTO mockResponse = new LatestRatesResponseDTO();
        mockResponse.setBase("USD");
        mockResponse.setDate("2026-02-13");
        mockResponse.setRates(Map.of("IDR", BigDecimal.valueOf(15500)));

        Mockito.when(restTemplate.getForObject(
                anyString(),
                eq(LatestRatesResponseDTO.class)
        )).thenReturn(mockResponse);

        LatestRatesResponseDTO result =
                client.getLatestRates("USD", "IDR");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("USD", result.getBase());
        Assertions.assertTrue(result.getRates().containsKey("IDR"));

        Mockito.verify(restTemplate)
                .getForObject(contains("/latest"), eq(LatestRatesResponseDTO.class));
    }
}
