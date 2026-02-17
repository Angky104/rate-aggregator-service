package com.allobank.angky.rateaggregatorservice.constant;

import com.allobank.angky.rateaggregatorservice.exception.InvalidResourceTypeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ResourceType {

    LATEST_IDR_RATES("latest_idr_rates"),
    HISTORICAL_IDR_USD("historical_idr_usd"),
    SUPPORTED_CURRENCIES("supported_currencies");

    private final String value;

    ResourceType(String value) {
        this.value = value;
    }

    public static ResourceType fromValue(String value) {
        return Arrays.stream(values())
                .filter(v -> v.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new InvalidResourceTypeException(value));
    }
}

