package com.sample.countries.service;

import com.sample.countries.DemoApplication;
import com.sample.countries.model.CountryEntity;
import com.sample.countries.model.Name;
import com.sample.countries.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = DemoApplication.class)
public class CountryServiceIT {

    @Autowired
    private CountryServiceImpl countryService;


    @Test
    public void testGetCountriesSortedByPopulationDensity() {

        List<CountryEntity> sortedCountries = countryService.getCountriesSortedByPopulationDensity();

        assertTrue(() -> {
            for (int i = 0; i < sortedCountries.size() - 1; i++) {
                if (sortedCountries.get(i).getPopulationDensity() < sortedCountries.get(i + 1).getPopulationDensity()) {
                    return false;
                }
            }
            return true;
        });

    }

    @Test
    public void testGetAsianCountryWithMostBorderingCountries() {

        CountryEntity resultCountry = countryService.getCountryWithMostBorderingByRegion("asia");
        assertNotNull(resultCountry);

        CountryEntity expectedCountry = new CountryEntity(new Name("Turkey"), 783562, 84339067, "Asia", "TUR", null);
        assertEquals(expectedCountry, resultCountry);
    }


}

