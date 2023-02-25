package com.mawen.movie.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用 {@link RibbonClient}，为特定的name的Ribbon Client自定义配置
 * 使用 {@link RibbonClient#configuration()}指定Ribbon的配置类
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see RibbonClient
 * @see RibbonConfiguration
 * @since 2023/2/25
 */
@Configuration
@RibbonClient(name = "microservice-provider-user", configuration = RibbonConfiguration.class)
public class TestConfiguration {
}
