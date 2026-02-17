package com.allobank.angky.rateaggregatorservice.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HistoricalRateDTO {
    private String date;
    private BigDecimal usdRate;
}
