package com.mawen.movie.controller;

import com.mawen.movie.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/23
 */
@RestController
public class MovieController {

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable long id) {
//        return this.restTemplate.getForObject("http://localhost:8000/" + id, User.class); // 硬编码的方式
        return this.restTemplate.getForObject(this.userServiceUrl + id, User.class); // 读取配置文件
    }

    /**
     * 查询 microservice-provider-user 服务信息并返回
     *
     * @return microservice-provider-user 服务的信息
     */
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return this.discoveryClient.getInstances("microservice-provider-user");
    }

}
