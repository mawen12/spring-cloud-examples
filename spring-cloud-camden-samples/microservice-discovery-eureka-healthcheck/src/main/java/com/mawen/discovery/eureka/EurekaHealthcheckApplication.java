package com.mawen.discovery.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/24
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaHealthcheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaHealthcheckApplication.class, args);
    }

}
