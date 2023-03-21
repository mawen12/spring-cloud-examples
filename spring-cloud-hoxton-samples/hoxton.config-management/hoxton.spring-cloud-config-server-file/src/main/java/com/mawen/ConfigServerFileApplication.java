package com.mawen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/18
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerFileApplication.class, args);
    }

}
