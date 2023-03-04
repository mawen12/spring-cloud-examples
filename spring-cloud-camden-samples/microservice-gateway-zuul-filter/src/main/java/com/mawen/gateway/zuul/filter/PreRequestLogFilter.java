package com.mawen.gateway.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * PRE 请求过滤器
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/3
 */
@Slf4j
public class PreRequestLogFilter extends ZuulFilter {

    /**
     * PRE 前置过滤
     *
     * @return 过滤类型
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤顺序
     *
     * @return 过滤顺序
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 过滤
     *
     * @return 过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 打印HTTP方法以及请求地址
     *
     * @return null
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("send {} request to {}",request.getMethod(), request.getRequestURL().toString());

        return null;
    }
}
