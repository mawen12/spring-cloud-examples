package com.mawen.movie.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon 配置类
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/25
 */
@Configuration
public class RibbonConfiguration {

    /**
     * 设置负载均衡规则
     *
     * @return 负载均衡示例
     */
    @Bean
    public IRule ribbonRule() {
        // 随机
        return new RandomRule();
    }

}
