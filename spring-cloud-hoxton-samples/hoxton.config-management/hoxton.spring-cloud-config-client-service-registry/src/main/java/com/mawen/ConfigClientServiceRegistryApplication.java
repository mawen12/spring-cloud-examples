package com.mawen;

import com.alibaba.cloud.nacos.util.InetIPv6Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/18
 */
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
public class ConfigClientServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientServiceRegistryApplication.class, args);
    }

    @Bean
    @ConditionalOnMissingBean
    public InetIPv6Utils inetIPv6Utils(InetUtilsProperties properties) {
        return new InetIPv6Utils(properties);
    }
}
