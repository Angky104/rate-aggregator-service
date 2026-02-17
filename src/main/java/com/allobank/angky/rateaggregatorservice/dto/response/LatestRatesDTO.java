package com.allobank.angky.rateaggregatorservice.dto.response;

import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Setter
public class LatestRatesDTO {
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;
    private BigDecimal usdBuySpreadIdr;
}
