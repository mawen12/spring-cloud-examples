package com.mawen.ribbon.rule;

import com.mawen.ribbon.predicate.DiscoveryEnabledPredicate;
import com.mawen.ribbon.predicate.MetadataAwarePredicate;
import com.mawen.ribbon.support.RibbonRuleProperties;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PatternMatchUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/9
 */
public class MetadataAwareRule extends DiscoveryEnabledRule {

    @Autowired(required = false)
    private List<RibbonCustomRule> rules;

    @Autowired
    private RibbonRuleProperties properties;

    public MetadataAwareRule() {
        super(MetadataAwarePredicate.INSTANCE);
    }

    public MetadataAwareRule(DiscoveryEnabledPredicate discoveryEnabledPredicate) {
        super(discoveryEnabledPredicate);
    }

    @Override
    public List<Server> filterServers(List<Server> serverList) {
        List<String> priorIpPattern = properties.getPriorIpPattern();
        boolean hasPriorIpPattern = !priorIpPattern.isEmpty();
        String[] priorIpPatterns = priorIpPattern.toArray(new String[0]);
        List<Server> priorServerList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(rules)) {
            for (RibbonCustomRule rule : rules) {
                priorServerList = rule.filterServers(serverList);
                if (!CollectionUtils.isEmpty(priorServerList)) {
                    return priorServerList;
                }
            }
        }

        for (Server server : serverList) {
            String host = server.getHost();
            if (hasPriorIpPattern && PatternMatchUtils.simpleMatch(priorIpPatterns, host)) {
                priorServerList.add(server);
            }
        }

        if (!priorServerList.isEmpty()) {
            return priorServerList;
        } else {
            return Collections.unmodifiableList(serverList);
        }
    }
}
