package com.allobank.angky.rateaggregatorservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "frankfurter")
@Data
public class FrankfurterProperties {

    private String baseUrl;
    private String apiVersion;
    private Endpoints endpoints;

    @Data
    public static class Endpoints {
        private String latest;
        private String historical;
        private String currencies;
    }

    public String buildUrl(String endpoint) {
        return baseUrl + apiVersion + endpoint;
    }
}


