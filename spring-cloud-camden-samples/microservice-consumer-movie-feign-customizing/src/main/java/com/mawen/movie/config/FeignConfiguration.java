package com.mawen.movie.config;

import feign.Contract;
import feign.Feign;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * {@link Feign} 配置类
 * 如果想要不被所有的 {@link FeignClient} 共享，那么就需要显示指定 {@link ComponentScan} 不扫描该类所在的包
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/25
 */
@Configuration
public class FeignConfiguration {

    /**
     * 将契约改为feign原生契约，这样就可以使用feign自带的注解
     *
     * @return 默认的feign契约
     */
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }


}
