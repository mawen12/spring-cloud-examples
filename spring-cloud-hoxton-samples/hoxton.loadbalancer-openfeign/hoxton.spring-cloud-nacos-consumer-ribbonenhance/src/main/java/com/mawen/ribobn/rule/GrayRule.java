package com.mawen.ribobn.rule;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.mawen.ribobn.context.RibbonRequestContextHolder;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 基于 Nacos 金丝雀发布规则
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/15
 */
public class GrayRule extends AbstractLoadBalancerRule {

    private Random random = new Random();

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        boolean grayInvocation = false;

        try {
            // 获取请求的Gray表示
            String grayTag = RibbonRequestContextHolder.getCurrentContext().get("Gray");
            if (!StringUtils.isEmpty(grayTag) && grayTag.equals(Boolean.TRUE.toString())) {
                grayInvocation = true;
            }

            List<Server> serverList = this.getLoadBalancer().getReachableServers();
            List<Server> grayList = new ArrayList<>();
            List<Server> normalServerList = new ArrayList<>();
            // 从目标服务中读取gray值，且进行分组
            for (Server server : serverList) {
                NacosServer nacosServer = (NacosServer) server;
                if (nacosServer.getMetadata().containsKey("gray") && nacosServer.getMetadata().get("gray").equals("true")) {
                    grayList.add(server);
                } else {
                    normalServerList.add(server);
                }
            }

            //  获取调用
            if (grayInvocation) {
                return grayList.get(random.nextInt(grayList.size()));
            } else {
                return normalServerList.get(random.nextInt(normalServerList.size()));
            }
        } finally {
            RibbonRequestContextHolder.clearContext();
        }
    }
}
