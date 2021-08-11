package com.async.mixer.mixer.model;

import lombok.Data;

public @Data class Comment {
    private String name;
    private int postId;
    private int id;
    private String body;
    private String email;
}