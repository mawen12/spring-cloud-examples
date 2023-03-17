package com.mawen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/14
 */
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
@EnableFeignClients
public class NacosConsumer4OpenFeign {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumer4OpenFeign.class, args);
    }

    @FeignClient(name = "nacos-provider-lb", configuration = MyOpenFeignConfiguration.class, contextId = "jaxrsFeign")
    interface EchoServiceJAXRS {

        @GET
        @Path("/")
        String echo();
    }

    @FeignClient(name = "nacos-provider-lb", contextId = "springmvcFeign")
    interface EchoServiceSpringMVC {

        @GetMapping("/")
        String echo();
    }

    @RestController
    class HelloController {

        @Autowired
        private EchoServiceJAXRS echoServiceJAXRS;

        @Autowired
        private EchoServiceSpringMVC echoServiceSpringMVC;

        @GetMapping("/jaxrs")
        public String jaxrs() {
            return echoServiceJAXRS.echo();
        }

        @GetMapping("/springmvc")
        public String springmvc() {
            return echoServiceSpringMVC.echo();
        }
    }
}
