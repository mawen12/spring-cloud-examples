package com.mawen.movie.feignclient;

import com.mawen.movie.config.FeignConfiguration;
import com.mawen.movie.pojo.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/25
 */
@FeignClient(name = "microservice-provider-user",
        configuration = FeignConfiguration.class) // 指定feign的配置类
public interface UserFeignClient {

    @RequestLine("GET /{id}")
    public User findById(@Param("id") long id);

}
