package com.mawen.gateway.zuul.config;

import com.mawen.gateway.zuul.filter.PreRequestLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/4
 */
@Configuration
public class ZuulConfig {

    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }

}
