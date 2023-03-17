package com.mawen.ribobn.interceptor;

import com.mawen.ribobn.context.RibbonRequestContextHolder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

/**
 * 金丝雀发布拦截器
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/15
 */
public class GrayInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (request.getHeaders().containsKey("Gray")) {
            String value = request.getHeaders().getFirst("Gray");
            if (value.equals("true")) {
                // 将带有Gray表示的请求放到保持器中
                RibbonRequestContextHolder.getCurrentContext().put("Gray", Boolean.TRUE.toString());
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                attributes.setAttribute("Gray", Boolean.TRUE.toString(), 0);
            }
        }
        return execution.execute(request, body);
    }
}
