package com.sample.countries.config;

import com.sample.countries.model.CountryEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.openapitools.model.Country;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching
public class WebConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // setup
        TypeMap<CountryEntity, Country> propertyMapper = modelMapper.createTypeMap(CountryEntity.class, Country.class);
        //flatten the property
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getName().getCommon(), Country::setName)
        );
        return modelMapper;
    }
}
