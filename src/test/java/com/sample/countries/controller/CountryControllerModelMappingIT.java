package com.sample.countries.controller;

import com.sample.countries.DemoApplication;
import com.sample.countries.model.CountryEntity;
import com.sample.countries.model.Name;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.openapitools.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DemoApplication.class)
public class CountryControllerModelMappingIT {


    @Autowired
    private ModelMapper mapper;


    @Test
    public void testCountryEntityToCountryMapping() {
        // when similar source object is provided
        CountryEntity countryEntity = new CountryEntity(new Name("Eritrea"), 117600, 5352000, "Africa", "ERI", new String[]{
                "DJI",
                "ETH",
                "SDN"
        });

        Country country = this.mapper.map(countryEntity, Country.class);

        // then it maps by default
        assertEquals(countryEntity.getName().getCommon(), country.getName());
        assertEquals(countryEntity.getCca3(), country.getCca3());
    }
}
