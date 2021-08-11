package com.async.mixer.mixer.feignclients;

import com.async.mixer.mixer.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "userClient", url = "https://jsonplaceholder.typicode.com/")
public interface UserFeignClient {

    @GetMapping(path = "users/1")
    User getUser();

}
