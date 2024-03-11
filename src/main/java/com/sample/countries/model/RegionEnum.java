package com.sample.countries.model;

import java.util.HashSet;
import java.util.Set;

public enum RegionEnum {
    ASIA,
    EUROPE,
    AFRICA,
    Americas,
    Oceania,
    Antarctic;

    private static final Set<String> values = new HashSet<>();

    static {
        for (RegionEnum choice : RegionEnum.values()) {
            values.add(choice.name());
        }
    }

    public static boolean contains(String value) {
        return values.contains(value);
    }

    public static boolean containsIgnoreCase(String value) {
        return values.contains(value.toUpperCase());
    }
}
