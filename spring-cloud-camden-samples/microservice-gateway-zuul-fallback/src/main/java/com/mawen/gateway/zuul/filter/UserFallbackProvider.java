package com.mawen.gateway.zuul.filter;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * User 回退
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/4
 */
public class UserFallbackProvider implements ZuulFallbackProvider {

    /**
     * 表明是为哪个微服务提供回退
     *
     * @return 微服务名称
     */
    @Override
    public String getRoute() {
        return "microservice-provider-user";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // fallback 时的状态码
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // 数字类型的状态码，本例返回的其实就是200
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                // 状态文本，本例返回的其实就是OK
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                // 响应体
                return new ByteArrayInputStream("用户微服务不可用，请稍后再试。".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // headers 设定
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", StandardCharsets.UTF_8);
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
