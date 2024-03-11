package com.sample.countries.controller;

import com.sample.countries.model.CountryEntity;
import com.sample.countries.service.impl.CountryServiceImpl;
import org.modelmapper.ModelMapper;
import org.openapitools.api.CountriesApi;
import org.openapitools.model.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CountryController implements CountriesApi {

    private final CountryServiceImpl countryService;
    private final ModelMapper modelMapper;

    public CountryController(CountryServiceImpl countryService, ModelMapper modelMapper) {
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResponseEntity<List<Country>> getCountries() {
        List<CountryEntity> countries = countryService.getCountriesNoFilter();
        return ResponseEntity.ok(countries.stream()
                .map(entity -> modelMapper.map(entity, Country.class))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<Country>> getCountriesByPopulationDensity() {
        List<CountryEntity> countries = countryService.getCountriesSortedByPopulationDensity();
        return ResponseEntity.ok(countries.stream()
                .map(entity -> modelMapper.map(entity, Country.class))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Country> getCountryWithMostBorderingByRegion(String region) {
        CountryEntity country = countryService.getCountryWithMostBorderingByRegion(region);
        return ResponseEntity.ok(modelMapper.map(country, Country.class));
    }

}

