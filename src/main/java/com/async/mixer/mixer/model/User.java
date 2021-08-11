package com.async.mixer.mixer.model;

import lombok.Data;

public @Data class User {
    private String website;
    private Address address;
    private String phone;
    private String name;
    private Company company;
    private int id;
    private String email;
    private String username;
}