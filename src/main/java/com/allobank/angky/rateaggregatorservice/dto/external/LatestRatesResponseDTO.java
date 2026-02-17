package com.allobank.angky.rateaggregatorservice.dto.external;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class LatestRatesResponseDTO {

    private BigDecimal amount;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;

}
