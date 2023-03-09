package com.mawen.ribbon.rule;

import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/9
 */
public interface RibbonCustomRule {

    List<Server> filterServers(List<Server> serverList);

}
