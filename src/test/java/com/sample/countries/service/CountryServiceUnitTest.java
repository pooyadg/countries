package com.sample.countries.service;

import com.sample.countries.exception.ServiceException;
import com.sample.countries.model.CountryEntity;
import com.sample.countries.model.Name;
import com.sample.countries.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CountryServiceUnitTest {


    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private ExternalCountryApiService externalCountryApiService;


    @Test
    public void testGetCountriesSortedByPopulationDensity() {
        when(externalCountryApiService.getCountries())
                .thenReturn(getSampleCountries());

        List<CountryEntity> sortedCountries = countryService.getCountriesSortedByPopulationDensity();
        assertEquals(sortedCountries, getSampleCountriesSortedByPopulationDensity());

    }

    @Test
    public void testGetAsianCountryWithMostBorderingCountries() {
        when(externalCountryApiService.getCountries())
                .thenReturn(getSampleCountries());

        CountryEntity resultCountry = countryService.getCountryWithMostBorderingByRegion("asia");

        assertNotNull(resultCountry);

        CountryEntity expectedCountry = new CountryEntity(new Name("Turkey"), 783562, 84339067, "Asia", "TUR", null);

        assertEquals(expectedCountry, resultCountry);
    }


    @Test
    public void testUnHappyGetAsianCountryWithMostBorderingCountriesNoAsian() {

        when(externalCountryApiService.getCountries())
                .thenReturn(getSampleCountriesNoAsian());


        Exception exception = assertThrows(ServiceException.class, () -> {
            countryService.getCountryWithMostBorderingByRegion("asia");
        });

        String expectedMessage = "Failed to fetch countries data from API or no country found.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    private List<CountryEntity> getSampleCountries() {
        // Return mock countries data for testing
        List<CountryEntity> countries = new ArrayList<>();
        countries.add(new CountryEntity(new Name("Eritrea"), 117600, 5352000, "Africa", "ERI", new String[]{
                "DJI",
                "ETH",
                "SDN"
        }));
        countries.add(new CountryEntity(new Name("Cyprus"), 9251, 1207361, "Europe", "CYP", null));
        countries.add(new CountryEntity(new Name("Turkey"), 783562, 84339067, "Asia", "TUR", new String[]{
                "ARM",
                "AZE",
                "BGR",
                "GEO",
                "GRC",
                "IRN",
                "IRQ",
                "SYR"
        }));
        countries.add(new CountryEntity(new Name("Singapore"), 710, 5685807, "Asia", "SGP", null));


        return countries;
    }

    private List<CountryEntity> getSampleCountriesNoAsian() {
        // Return mock countries data for testing
        List<CountryEntity> countries = new ArrayList<>();
        countries.add(new CountryEntity(new Name("Eritrea"), 117600, 5352000, "Africa", "ERI", new String[]{
                "DJI",
                "ETH",
                "SDN"
        }));
        countries.add(new CountryEntity(new Name("Cyprus"), 9251, 1207361, "Europe", "CYP", null));

        return countries;
    }

    private List<CountryEntity> getSampleCountriesSortedByPopulationDensity() {
        // Return mock countries data for testing
        List<CountryEntity> countries = new ArrayList<>();
        countries.add(new CountryEntity(new Name("Singapore"), 710, 5685807, "Asia", "SGP", null));
        countries.add(new CountryEntity(new Name("Cyprus"), 9251, 1207361, "Europe", "CYP", null));
        countries.add(new CountryEntity(new Name("Turkey"), 783562, 84339067, "Asia", "TUR", new String[]{
                "ARM",
                "AZE",
                "BGR",
                "GEO",
                "GRC",
                "IRN",
                "IRQ",
                "SYR"
        }));
        countries.add(new CountryEntity(new Name("Eritrea"), 117600, 5352000, "Africa", "ERI", new String[]{
                "DJI",
                "ETH",
                "SDN"
        }));
        return countries;
    }

}

