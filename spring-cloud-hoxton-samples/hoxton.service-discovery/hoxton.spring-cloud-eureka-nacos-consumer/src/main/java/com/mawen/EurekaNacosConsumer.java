package com.mawen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/13
 */
@SpringBootApplication
@EnableConfigurationProperties({AutoServiceRegistrationProperties.class})
public class EurekaNacosConsumer {

    public static void main(String[] args) {
        SpringApplication.run(EurekaNacosConsumer.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RestController
    class HelloController {

        @Autowired
        private DiscoveryClient discoveryClient;

        @Autowired
        private RestTemplate restTemplate;

        private String serviceName = "my-provider";

        @GetMapping("/info")
        public String info() {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            StringBuilder sb = new StringBuilder();
            sb.append("All Services: " + discoveryClient.getServices() + "<br/>");
            sb.append("my-provider instance list: <br/>");
            serviceInstances.forEach(serviceInstance -> {
                sb.append("[serviceId: " + serviceInstance.getServiceId() +
                        ", host: " + serviceInstance.getHost() +
                        ", port: " + serviceInstance.getPort() + " ]");
                sb.append("<br/>");
            });
            return sb.toString();
        }

        @GetMapping("/hello")
        public String hello() {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            ServiceInstance serviceInstance = serviceInstances.stream().findAny().orElseThrow(() -> new IllegalStateException("no " + serviceName + " instance available"));
            return restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/echo?name=nacos", String.class);
        }

    }
}
