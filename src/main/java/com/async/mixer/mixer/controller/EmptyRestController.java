package com.async.mixer.mixer.controller;

import com.async.mixer.mixer.feignclients.CommentsFeignClient;
import com.async.mixer.mixer.feignclients.PostsFeignClient;
import com.async.mixer.mixer.feignclients.UserFeignClient;
import com.async.mixer.mixer.model.Comment;
import com.async.mixer.mixer.model.Mixed;
import com.async.mixer.mixer.model.Post;
import com.async.mixer.mixer.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping(path = "sync/mixed")
    public ResponseEntity<Mixed> mixed() {
        User user = userFeignClient.getUser();
        List<Post> posts = postsFeignClient.getPosts();
        List<Comment> comments = commentsFeignClient.getComments();
        Mixed mixed = new Mixed();
        mixed.setUser(user);
        mixed.setPosts(posts);
        mixed.setComments(comments);
        return ResponseEntity.ok(mixed);
    }

    @GetMapping(path = "async/mixed")
    public CompletableFuture<ResponseEntity<Mixed>> mixedAsync() {
        return getUser().thenCombine(getPosts(),
                (userResponseEntity, postsResponseEntity) -> {
                    Mixed mixed = new Mixed();
                    mixed.setUser(userResponseEntity.getBody());
                    mixed.setPosts(postsResponseEntity.getBody());
                    return ResponseEntity.ok(mixed);
                }).thenCombine(getComments(), (mixedResponseEntity, commentsResponseEntity) -> {
            Mixed mixed = mixedResponseEntity.getBody();
            requireNonNull(mixed).setComments(commentsResponseEntity.getBody());
            return ResponseEntity.ok(mixed);
        }).handle((combinedResult, e) -> {
            if (e != null) {
                log.info(">>>>> Error = {}", e.getMessage(), e);
                // This is needed for controller advice to kick-in
                throw new CompletionException(e);
            }
            if (combinedResult != null) {
                log.info(">>>>> OK = {}", requireNonNull(combinedResult.getBody()).getUser().getId());
            }
            return combinedResult;
        });
    }
}