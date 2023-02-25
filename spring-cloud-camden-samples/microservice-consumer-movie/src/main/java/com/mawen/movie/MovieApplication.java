package com.mawen.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/23
 */
@EnableDiscoveryClient // 声明为 Eureka Client
@SpringBootApplication
public class MovieApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

}
