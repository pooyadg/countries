package com.sample.countries.service;

import com.sample.countries.exception.CountryApiException;
import com.sample.countries.model.CountryEntity;
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
public interface ExternalCountryApiService {


    /**
     * Get countries from the RestCountries API
     *
     * @return List of countries from the RestCountries API
     * @throws CountryApiException If the API call fails
     */
    List<CountryEntity> getCountries();
}
