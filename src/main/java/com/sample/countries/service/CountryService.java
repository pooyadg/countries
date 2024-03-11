package com.sample.countries.service;

import com.sample.countries.model.CountryEntity;

import java.util.List;


public interface CountryService {
    /**
     * Get all countries with no filters
     *
     * @return List of countries with no filters
     */
    List<CountryEntity> getCountriesNoFilter();


    /**
     * Get countries sorted by population density in descending order
     *
     * @return List of countries sorted by population density in descending order
     */
    List<CountryEntity> getCountriesSortedByPopulationDensity();

    /**
     * Get country with most bordering countries in different region
     *
     * @param region Region to filter countries by
     * @return Country with most bordering countries in different region
     */
    CountryEntity getCountryWithMostBorderingByRegion(String region);
}