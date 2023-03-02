package com.mawen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/2
 */
@EnableTurbineStream
@SpringBootApplication
public class HystrixTurbineMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineMQApplication.class, args);
    }
}
