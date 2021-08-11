package com.async.mixer.mixer.model;

import lombok.Data;

import java.util.List;

public @Data class Mixed {

    private User user;
    private List<Post> posts;
    private List<Comment> comments;

}
