package com.allobank.angky.rateaggregatorservice.controller;

import com.allobank.angky.rateaggregatorservice.exception.GlobalExceptionHandler;
import com.allobank.angky.rateaggregatorservice.service.FinanceDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

class FinanceDataControllerTest {

    private MockMvc mockMvc;

    private FinanceDataService financeDataService;
    private FinanceDataController controller;

    @BeforeEach
    void setup() {
        financeDataService = Mockito.mock(FinanceDataService.class);
        controller = new FinanceDataController(financeDataService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnOkForValidResourceType() throws Exception {

        Mockito.when(financeDataService.getData(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/data/latest_idr_rates")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReturnBadRequestForInvalidResourceType() throws Exception {

        Mockito.when(financeDataService.getData("wrong_type"))
                .thenThrow(new IllegalArgumentException("Invalid resource type"));

        mockMvc.perform(MockMvcRequestBuilders.get("/data/wrong_type")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
