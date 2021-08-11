package com.async.mixer.mixer.controller;

import com.async.mixer.mixer.feignclients.CommentsFeignClient;
import com.async.mixer.mixer.feignclients.PostsFeignClient;
import com.async.mixer.mixer.feignclients.UserFeignClient;
import com.async.mixer.mixer.model.Comment;
import com.async.mixer.mixer.model.Post;
import com.async.mixer.mixer.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class EmptyRestController {

    private final UserFeignClient userFeignClient;
    private final PostsFeignClient postsFeignClient;
    private final CommentsFeignClient commentsFeignClient;

    @GetMapping(path = "user")
    public CompletableFuture<ResponseEntity<User>> getUser() {
        return CompletableFuture.supplyAsync(() ->
                ResponseEntity.ok(userFeignClient.getUser()));
    }

    @GetMapping(path = "posts")
    public CompletableFuture<ResponseEntity<List<Post>>> getPosts() {
        return CompletableFuture.supplyAsync(() ->
                ResponseEntity.ok(postsFeignClient.getPosts()));
    }

    @GetMapping(path = "comments")
    public CompletableFuture<ResponseEntity<List<Comment>>> getComments() {
        return CompletableFuture.supplyAsync(() ->
                ResponseEntity.ok(commentsFeignClient.getComments()));
    }
}