package com.allobank.angky.rateaggregatorservice;

import com.allobank.angky.rateaggregatorservice.controller.FinanceDataController;
import com.allobank.angky.rateaggregatorservice.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FinanceDataController(null))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/finance/data/wrong_type"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
