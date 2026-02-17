package com.allobank.angky.rateaggregatorservice.client;

import com.allobank.angky.rateaggregatorservice.config.FrankfurterProperties;
import com.allobank.angky.rateaggregatorservice.dto.external.HistoricalRatesResponseDTO;
import com.allobank.angky.rateaggregatorservice.dto.external.LatestRatesResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FrankfurterApiClient {

    private final RestTemplate restTemplate;
    private final FrankfurterProperties props;

    // ✅ Latest Rates
    public LatestRatesResponseDTO getLatestRates(String base, String symbols) {

        String url = UriComponentsBuilder
                .fromHttpUrl(props.buildUrl(props.getEndpoints().getLatest()))
                .queryParam("base", base)
                .queryParam("symbols", symbols)
                .toUriString();

        return restTemplate.getForObject(url, LatestRatesResponseDTO.class);
    }

    // ✅ Historical Rates
    public HistoricalRatesResponseDTO getHistoricalRates(
            String dateFrom,
            String dateTo,
            String from,
            String to
    ) {

        String dateRange = "/" + dateFrom + ".." + dateTo;

        String url = UriComponentsBuilder
                .fromHttpUrl(props.getBaseUrl() + props.getApiVersion() + dateRange)
                .queryParam("from", from)
                .queryParam("to", to)
                .toUriString();

        return restTemplate.getForObject(url, HistoricalRatesResponseDTO.class);
    }

    // ✅ Supported Currencies
    public Map<String, String> getCurrencies() {

        String url = props.buildUrl(props.getEndpoints().getCurrencies());

        ResponseEntity<Map<String, String>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Map<String, String>>() {}
                );

        return response.getBody();
    }
}
