package com.sample.countries.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CountryEntity {
    private Name name;
    private long area;
    private long population;
    private String region;
    @EqualsAndHashCode.Include
    private String cca3;
    private String[] borders;

    @Getter(lazy = true)
    private final double populationDensity = area == 0 ? -1 : (double) population / area;

}


