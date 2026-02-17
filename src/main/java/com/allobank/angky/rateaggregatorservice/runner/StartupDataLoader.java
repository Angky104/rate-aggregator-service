package com.allobank.angky.rateaggregatorservice.runner;

import com.allobank.angky.rateaggregatorservice.client.FrankfurterApiClient;
import com.allobank.angky.rateaggregatorservice.dto.external.HistoricalRatesResponseDTO;
import com.allobank.angky.rateaggregatorservice.dto.external.LatestRatesResponseDTO;
import com.allobank.angky.rateaggregatorservice.dto.response.CurrencyDTO;
import com.allobank.angky.rateaggregatorservice.dto.response.HistoricalRateDTO;
import com.allobank.angky.rateaggregatorservice.dto.response.LatestRatesDTO;
import com.allobank.angky.rateaggregatorservice.store.InMemoryDataStore;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StartupDataLoader implements ApplicationRunner {

    private final FrankfurterApiClient apiClient;
    private final InMemoryDataStore dataStore;

    @Override
    public void run(ApplicationArguments args) {

        // ✅ Latest Rates
        LatestRatesResponseDTO latestResponse =
                apiClient.getLatestRates("IDR", "USD");

        // ✅ Historical Rates
        HistoricalRatesResponseDTO historicalResponse =
                apiClient.getHistoricalRates(
                        "2024-01-01",
                        "2024-01-05",
                        "IDR",
                        "USD"
                );

        // ✅ Currencies
        Map<String, String> currenciesResponse =
                apiClient.getCurrencies();

        // ✅ Transform → DTO Response
        List<LatestRatesDTO> latestRates =
                transformLatest(latestResponse);

        List<HistoricalRateDTO> historicalRates =
                transformHistorical(historicalResponse);

        List<CurrencyDTO> currencies =
                transformCurrencies(currenciesResponse);

        // ✅ Store ke memory
        dataStore.setData(latestRates, historicalRates, currencies);
    }

    private List<LatestRatesDTO> transformLatest(LatestRatesResponseDTO response) {

        BigDecimal usdRate = response.getRates().get("USD");

        BigDecimal spreadFactor = new BigDecimal("0.02");

        BigDecimal usdBuySpread = BigDecimal.ONE
                .divide(usdRate, 10, RoundingMode.HALF_UP)
                .multiply(BigDecimal.ONE.add(spreadFactor));

        LatestRatesDTO dto = new LatestRatesDTO();
        dto.setBase(response.getBase());
        dto.setDate(response.getDate());
        dto.setRates(response.getRates());
        dto.setUsdBuySpreadIdr(usdBuySpread);

        return List.of(dto);
    }

    private List<HistoricalRateDTO> transformHistorical(
            HistoricalRatesResponseDTO response
    ) {

        List<HistoricalRateDTO> list = new ArrayList<>();

        response.getRates().forEach((date, rateMap) -> {

            BigDecimal usdRate = rateMap.get("USD");

            HistoricalRateDTO dto = new HistoricalRateDTO();
            dto.setDate(date);
            dto.setUsdRate(usdRate);

            list.add(dto);
        });

        return list;
    }

    private List<CurrencyDTO> transformCurrencies(Map<String, String> response) {

        return response.entrySet()
                .stream()
                .map(entry -> {
                    CurrencyDTO dto = new CurrencyDTO();
                    dto.setCode(entry.getKey());
                    dto.setName(entry.getValue());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
