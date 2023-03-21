package com.mawen.config;

import com.mawen.ribbon.interceptor.GrayInterceptor;
import com.mawen.ribbon.interceptor.GrayRequestInterceptor;
import com.mawen.ribbon.rule.GrayRule;
import com.mawen.ribbon.rule.TrafficRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/19
 */
@Configuration
public class RibbonEnhanceConfiguration {

    @Bean
    @LoadBalanced
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(GrayInterceptor grayInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(grayInterceptor);
        return restTemplate;
    }

    @Bean
    public GrayInterceptor grayInterceptor(TrafficRule rule) {
        return new GrayInterceptor(rule);
    }

    @Bean
    public GrayRequestInterceptor requestInterceptor(TrafficRule rule) {
        return new GrayRequestInterceptor(rule);
    }

    @Bean
    public IRule myRule() {
        return new GrayRule();
    }


}
