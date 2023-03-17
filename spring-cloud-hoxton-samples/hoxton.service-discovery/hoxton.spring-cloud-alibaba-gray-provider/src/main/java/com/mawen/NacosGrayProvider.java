package com.mawen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/15
 */
@SpringBootApplication
public class NacosGrayProvider {

    public static void main(String[] args) {
        SpringApplication.run(NacosGrayProvider.class, args);
    }

    @RestController
    class EchoController {

        @GetMapping("/")
        public String echo(HttpServletRequest request) {
            return request.getLocalAddr() + ":" + request.getLocalPort();
        }
    }

}
