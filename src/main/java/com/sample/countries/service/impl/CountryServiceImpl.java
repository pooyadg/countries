package com.sample.countries.service.impl;

import com.sample.countries.exception.ServiceException;
import com.sample.countries.model.CountryEntity;
import com.sample.countries.service.CountryService;
import com.sample.countries.service.ExternalCountryApiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CountryServiceImpl implements CountryService {

    private final ExternalCountryApiService apiService;

    public CountryServiceImpl(ExternalCountryApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public List<CountryEntity> getCountriesNoFilter() {
        log.debug("Fetch countries data from api with no filter");
        return apiService.getCountries();
    }

    @Cacheable("countriesSortedByDensity")
    public List<CountryEntity> getCountriesSortedByPopulationDensity() {

        log.debug("Fetching countries data from API");
        List<CountryEntity> countries = apiService.getCountries();

        // Sort countries by population density
        countries.sort(Comparator.comparingDouble(CountryEntity::getPopulationDensity).reversed());
        return countries;
    }

    @Cacheable(value = "countryWithMostBordering", key = "#region")
    public CountryEntity getCountryWithMostBorderingByRegion(String region) {
        log.debug("Fetching countries data from API");
        List<CountryEntity> countryList = apiService.getCountries();
        Set<String> asianCountriesCca3;
        CountryEntity theCountry = null;
        asianCountriesCca3 = countryList.stream()
                .filter(country -> country.getRegion().equalsIgnoreCase(region))
                .map(CountryEntity::getCca3)
                .collect(Collectors.toSet());
        theCountry = countryList.stream()
                .filter(country -> country.getRegion().equalsIgnoreCase(region))
                .max(Comparator.comparingInt(country -> {
                            if (country.getBorders() == null || country.getBorders().length == 0)
                                return 0;
                            else {
                                return (int) Arrays.stream(country.getBorders())
                                        .filter(borderItem -> !asianCountriesCca3.contains(borderItem))
                                        .count();
                            }
                        }
                ))
                .orElse(null);

        if (theCountry == null) {
            throw new ServiceException("Failed to fetch countries data from API or no country found.");
        }
        return theCountry;
    }

}
