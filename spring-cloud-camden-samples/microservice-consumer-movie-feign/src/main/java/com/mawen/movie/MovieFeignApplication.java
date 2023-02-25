package com.mawen.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/23
 */
@EnableFeignClients // 启用 Feign
@EnableDiscoveryClient // 声明为 Eureka Client
@SpringBootApplication
public class MovieFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieFeignApplication.class, args);
    }

}
