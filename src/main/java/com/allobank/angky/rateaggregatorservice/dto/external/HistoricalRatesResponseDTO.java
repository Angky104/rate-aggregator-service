package com.allobank.angky.rateaggregatorservice.dto.external;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class HistoricalRatesResponseDTO {

    private BigDecimal amount;
    private String base;
    private Map<String, Map<String, BigDecimal>> rates;

}

