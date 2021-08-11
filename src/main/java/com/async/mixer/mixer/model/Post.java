package com.async.mixer.mixer.model;

import lombok.Data;

public @Data class Post {
    private int id;
    private String title;
    private String body;
    private int userId;
}