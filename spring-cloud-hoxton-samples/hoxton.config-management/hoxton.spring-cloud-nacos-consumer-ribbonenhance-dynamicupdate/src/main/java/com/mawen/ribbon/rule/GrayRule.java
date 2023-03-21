package com.mawen.ribbon.rule;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.mawen.ribbon.context.RibbonRequestContextHolder;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author <a href="mailto:1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/19
 */
public class GrayRule extends AbstractLoadBalancerRule {

    private Random random = new Random();

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        boolean grayInvocation = false;
        try {
            String grayTag = RibbonRequestContextHolder.getCurrentContext().get("Gray");
            if (!StringUtils.isEmpty(grayTag) && grayTag.equals(Boolean.TRUE.toString())) {
                grayInvocation = true;
            }

            List<Server> serverList = this.getLoadBalancer().getReachableServers();
            List<Server> grayServerList = new ArrayList<>();
            List<Server> normalServerList = new ArrayList<>();
            for (Server server : serverList) {
                NacosServer nacosServer = (NacosServer) server;
                if (nacosServer.getMetadata().containsKey("gray") && nacosServer.getMetadata().get("gray").equals("true")) {
                    grayServerList.add(nacosServer);
                } else {
                    normalServerList.add(nacosServer);
                }
            }

            if (grayInvocation) {
                return grayServerList.get(random.nextInt(grayServerList.size()));
            } else {
                return normalServerList.get(random.nextInt(normalServerList.size()));
            }
        } finally {
            RibbonRequestContextHolder.clear();
        }
    }
}
