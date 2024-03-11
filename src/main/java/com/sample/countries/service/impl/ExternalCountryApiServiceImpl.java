package com.sample.countries.service.impl;

import com.sample.countries.exception.CountryApiException;
import com.sample.countries.model.CountryEntity;
import com.sample.countries.service.ExternalCountryApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalCountryApiServiceImpl implements ExternalCountryApiService {


    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String allWithFiltersUrl;


    /**
     * Constructor for CountryApiService
     *
     * @param restTemplate
     * @param baseUrl
     * @param allWithFiltersUrl
     */
    public ExternalCountryApiServiceImpl(RestTemplate restTemplate,
                                         @Value("${restcountries.api.url}") String baseUrl,
                                         @Value("${restcountries.all.filtered.api.url}") String allWithFiltersUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.allWithFiltersUrl = allWithFiltersUrl;
    }


    @Cacheable("countries")
    public List<CountryEntity> getCountries() {

        ResponseEntity<List<CountryEntity>> response =
                restTemplate.exchange(baseUrl + allWithFiltersUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null || response.getBody().isEmpty()) {
            throw new CountryApiException("Failed to fetch countries data from API.");
        }
        return response.getBody();
    }

}
