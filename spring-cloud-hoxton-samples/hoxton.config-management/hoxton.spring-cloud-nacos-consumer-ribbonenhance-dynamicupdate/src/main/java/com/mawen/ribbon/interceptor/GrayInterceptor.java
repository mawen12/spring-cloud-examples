package com.mawen.ribbon.interceptor;

import com.mawen.ribbon.context.RibbonRequestContextHolder;
import com.mawen.ribbon.rule.TrafficRule;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * RestTemplate 拦截器
 *
 * @author <a href="mailto:1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/19
 */
public class GrayInterceptor implements ClientHttpRequestInterceptor {

    private TrafficRule rule;

    public GrayInterceptor(TrafficRule rule) {
        this.rule = rule;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (rule.getType().equalsIgnoreCase("header")) {
            if (request.getHeaders().containsKey(rule.getName())) {
                String value = request.getHeaders().get(rule.getName()).iterator().next();
                if (value.equals(rule.getValue())) {
                    RibbonRequestContextHolder.getCurrentContext().put("Gray", Boolean.TRUE.toString());
                }
            }
        } else if (rule.getType().equalsIgnoreCase("param")) {
            String query = request.getURI().getQuery();
            String[] queryKV = query.split("&");
            for (String queryItem : queryKV) {
                String[] queryInfo = queryItem.split("=");
                if (queryInfo[0].equalsIgnoreCase(rule.getName()) && queryInfo[1].equalsIgnoreCase(rule.getValue())) {
                    RibbonRequestContextHolder.getCurrentContext().put("Gray", Boolean.TRUE.toString());
                }
            }
        }
        return execution.execute(request,body);
    }
}
