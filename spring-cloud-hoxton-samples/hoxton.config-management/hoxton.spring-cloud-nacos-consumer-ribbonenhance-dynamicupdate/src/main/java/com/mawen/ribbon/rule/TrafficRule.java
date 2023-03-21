package com.mawen.ribbon.rule;

import com.mawen.ribbon.interceptor.GrayInterceptor;
import com.mawen.ribbon.interceptor.GrayRequestInterceptor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @see GrayInterceptor
 * @see GrayRequestInterceptor
 * @author <a href="mailto:1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/20
 */
@Data
@ConfigurationProperties(prefix = "traffic.rule")
public class TrafficRule {

    /**
     * 请求参数类型，param, header
     */
    private String type;

    /**
     * key
     */
    private String name;

    /**
     * value
     */
    private String value;

}
