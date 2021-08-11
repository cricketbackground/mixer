package com.async.mixer.mixer.model;

import lombok.Data;

public @Data class Address {
    private String zipcode;
    private Geo geo;
    private String suite;
    private String city;
    private String street;
}