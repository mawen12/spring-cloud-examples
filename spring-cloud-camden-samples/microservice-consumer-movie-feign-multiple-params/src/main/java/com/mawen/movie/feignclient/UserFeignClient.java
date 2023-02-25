package com.mawen.movie.feignclient;

import com.mawen.movie.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/25
 */
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // 暂不支持 @GetMapping
    public User findById(@PathVariable("id") long id);

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User get(@RequestParam("id") long id, @RequestParam("name") String name);

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public User post(@RequestBody User user);
}
