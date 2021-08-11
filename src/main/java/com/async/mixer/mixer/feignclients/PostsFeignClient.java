package com.async.mixer.mixer.feignclients;

import com.async.mixer.mixer.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "postsClient", url = "https://jsonplaceholder.typicode.com/")
public interface PostsFeignClient {

    @GetMapping(path = "posts?userId=1")
    List<Post> getPosts();

}
