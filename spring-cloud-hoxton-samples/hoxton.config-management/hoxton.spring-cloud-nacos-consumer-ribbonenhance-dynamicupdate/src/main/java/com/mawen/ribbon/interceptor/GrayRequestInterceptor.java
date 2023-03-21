package com.mawen.ribbon.interceptor;

import com.mawen.ribbon.context.RibbonRequestContextHolder;
import com.mawen.ribbon.rule.TrafficRule;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * OpenFeign 拦截器
 *
 * @author <a href="mailto:1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/20
 */
public class GrayRequestInterceptor implements RequestInterceptor {
    private TrafficRule rule;

    public GrayRequestInterceptor(TrafficRule rule) {
        this.rule = rule;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (rule.getType().equalsIgnoreCase("header")) {
            if (requestTemplate.headers().containsKey(rule.getName())) {
                String value = requestTemplate.headers().get(rule.getName()).iterator().next();
                if (value.equals(rule.getValue())) {
                    RibbonRequestContextHolder.getCurrentContext().put("Gray", Boolean.TRUE.toString());
                }
            }
        } else if (rule.getType().equalsIgnoreCase("param")) {
            if (requestTemplate.queries().containsKey(rule.getName())) {
                String value = requestTemplate.queries().get(rule.getName()).iterator().next();
                if (value.equals(rule.getValue())) {
                    RibbonRequestContextHolder.getCurrentContext().put("Gray", Boolean.TRUE.toString());
                }
            }
        }
    }
}
