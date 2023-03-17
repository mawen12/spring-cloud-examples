package com.mawen.ribobn.interceptor;

import com.mawen.ribobn.context.RibbonRequestContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/15
 */
public class GrayRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (requestTemplate.headers().containsKey("Gray")) {
            String value = requestTemplate.headers().get("Gray").iterator().next();
            if (value.equals("true")) {
                RibbonRequestContextHolder.getCurrentContext().put("Gray", Boolean.TRUE.toString());
            }
        }
    }
}
