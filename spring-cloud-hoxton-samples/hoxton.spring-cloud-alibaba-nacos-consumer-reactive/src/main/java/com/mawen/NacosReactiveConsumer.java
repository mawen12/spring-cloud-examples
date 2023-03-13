package com.mawen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/13
 */
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
public class NacosReactiveConsumer {

    public static void main(String[] args) {
        SpringApplication.run(NacosReactiveConsumer.class, args);
    }

    @RestController
    class HelloController {

        /**
         * 在 WebFlux 场景下，NacosReactiveDiscoveryClientConfiguration 会自动构造 NacosReactiveDiscoveryClient
         */
        @Autowired
        private ReactiveDiscoveryClient reactiveDiscoveryClient;

        @Autowired
        private DiscoveryClient discoveryClient;

        private String serviceName = "my-provider";

        @GetMapping("/services")
        public Flux<String> info() {
            return reactiveDiscoveryClient.getServices();
        }

        @GetMapping("/instances")
        public Flux<String> instances() {
            return reactiveDiscoveryClient.getInstances(serviceName).map(instance -> "[ serviceId " + instance.getServiceId() +
                    ", host: " + instance.getHost() +
                    ", port: " + instance.getPort() + " ]");
        }

        @GetMapping("/hello")
        public Mono<String> hello() {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            ServiceInstance serviceInstance = serviceInstances.stream().findAny().orElseThrow(() -> new IllegalStateException("no" + serviceName + " instance available"));
            // 使用 WebClient 这种 reactive 客户端去调用服务
            return WebClient.create("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort())
                    .get()
                    .uri("/echo?name=nacos")
                    .retrieve()
                    .bodyToMono(String.class);
        }
    }
}
