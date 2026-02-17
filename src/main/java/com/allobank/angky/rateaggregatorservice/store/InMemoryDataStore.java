package com.allobank.angky.rateaggregatorservice.store;

import com.allobank.angky.rateaggregatorservice.dto.response.CurrencyDTO;
import com.allobank.angky.rateaggregatorservice.dto.response.HistoricalRateDTO;
import com.allobank.angky.rateaggregatorservice.dto.response.LatestRatesDTO;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@Data
public class InMemoryDataStore {

    private List<LatestRatesDTO> latestRates;
    private List<HistoricalRateDTO> historicalRates;
    private List<CurrencyDTO> currencies;

    public void setData(
            List<LatestRatesDTO> latestRates,
            List<HistoricalRateDTO> historicalRates,
            List<CurrencyDTO> currencies
    ) {
        this.latestRates = List.copyOf(latestRates);
        this.historicalRates = List.copyOf(historicalRates);
        this.currencies = List.copyOf(currencies);
    }

}
