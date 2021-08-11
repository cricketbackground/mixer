package com.async.mixer.mixer.feignclients;

import com.async.mixer.mixer.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "commentsClient", url = "https://jsonplaceholder.typicode.com/")
public interface CommentsFeignClient {

    @GetMapping(path = "posts/1/commentsZZZ")
    List<Comment> getComments();

}
