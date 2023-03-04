package com.mawen.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/4
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerRefreshCloudBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerRefreshCloudBusApplication.class, args);
    }
}
