package com.sample.countries.service;

import com.sample.countries.exception.CountryApiException;
import com.sample.countries.service.impl.ExternalCountryApiServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ExternalApiServiceUnitTest {


    @Value("${restcountries.api.url}")
    private String baseUrl;

    @Value("${restcountries.all.filtered.api.url}")
    private String allWithFiltersUrl;

    @InjectMocks
    private ExternalCountryApiServiceImpl externalCountryApiService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testUnHappyGetCountries() {

        when(restTemplate
                .exchange(
                        anyString(),
                        eq(HttpMethod.GET),
                        isNull(),
                        any(ParameterizedTypeReference.class)))
                .thenReturn(ResponseEntity.ok(new ArrayList<>()));


        Exception exception = assertThrows(CountryApiException.class, () -> {
            externalCountryApiService.getCountries();
        });

        String expectedMessage = "Failed to fetch countries data from API.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}

