package com.mawen.movie.config;

import feign.Feign;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link Feign} 日志类
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/25
 */
@Configuration
public class FeignLogConfiguration {

    /**
     * NONE: 不记录任务日志（默认值）
     * <p>
     * BASIC: 仅记录请求方法、URL、响应状态代码以及执行事件
     * <p>
     * HEADERS: 记录 BASIC 级别的基础上，记录请求和响应的 header
     * <p>
     * FULL: 记录请求和响应的 header、body 和元数据
     *
     * @return 日志信息
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

}
