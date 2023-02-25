package com.mawen.movie.controller;

import com.mawen.movie.feignclient.UserFeignClient;
import com.mawen.movie.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/23
 */
@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable long id) {
        return this.userFeignClient.findById(id);
    }

    @GetMapping("/user")
    public User get(@RequestParam("id") long id, @RequestParam("name") String name) {
        return this.userFeignClient.get(id, name);
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        return this.userFeignClient.post(user);
    }

}
