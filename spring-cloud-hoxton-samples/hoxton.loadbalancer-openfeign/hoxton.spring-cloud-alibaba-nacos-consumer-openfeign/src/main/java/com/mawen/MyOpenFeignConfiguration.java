package com.mawen;

import feign.Contract;
import feign.jaxrs.JAXRSContract;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/14
 */
public class MyOpenFeignConfiguration {

    @Bean
    public Contract myFeignContract() {
        return new JAXRSContract();
    }
}
