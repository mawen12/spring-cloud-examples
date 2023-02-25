package com.mawen.movie.feignclient;

import com.mawen.movie.config.FeignLogConfiguration;
import com.mawen.movie.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/25
 */
@FeignClient(
        name = "microservice-provider-user",
        configuration = FeignLogConfiguration.class
)
public interface UserFeignClient {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // 暂不支持 @GetMapping
    public User findById(@PathVariable("id") long id);

}
