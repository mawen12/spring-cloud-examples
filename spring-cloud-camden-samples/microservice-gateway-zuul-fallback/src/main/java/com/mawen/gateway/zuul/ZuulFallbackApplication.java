package com.mawen.gateway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/2
 */
@SpringBootApplication
@EnableZuulProxy // 声明 Zuul 代理，并注册到 Eureka Server 上，且整合 Hystrix
public class ZuulFallbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulFallbackApplication.class, args);
    }
}
