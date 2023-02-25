package com.mawen.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/23
 */
@EnableDiscoveryClient // 声明为 Eureka Client
@SpringBootApplication
public class ProviderUserMetadataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderUserMetadataApplication.class, args);
    }
}
