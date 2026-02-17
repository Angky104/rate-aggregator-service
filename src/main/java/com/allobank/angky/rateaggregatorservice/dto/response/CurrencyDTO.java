package com.allobank.angky.rateaggregatorservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {

    private String code;
    private String name;

}
