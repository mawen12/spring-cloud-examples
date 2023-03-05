package com.mawen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/4
 */
@SpringBootApplication
@EnableZipkinStreamServer     // 声明一个 Zipkin Server
public class ZipkinServerStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerStreamApplication.class, args);
    }
}
