package com.mawen.ribbon.rule;

import com.mawen.ribbon.predicate.DiscoveryEnabledPredicate;
import com.netflix.loadbalancer.*;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/9
 */
public abstract class DiscoveryEnabledRule extends PredicateBasedRule {

    private final CompositePredicate predicate;

    public DiscoveryEnabledRule(DiscoveryEnabledPredicate discoveryEnabledPredicate) {
        Assert.notNull(discoveryEnabledPredicate, "Parameter '' can't be null");
        this.predicate = this.createCompositePredicate(discoveryEnabledPredicate, new AvailabilityPredicate(this, null));
    }

    private CompositePredicate createCompositePredicate(DiscoveryEnabledPredicate discoveryEnabledPredicate, AvailabilityPredicate availabilityPredicate) {
        return CompositePredicate.withPredicates(new AbstractServerPredicate[]{discoveryEnabledPredicate, availabilityPredicate}).build();
    }

    @Override
    public CompositePredicate getPredicate() {
        return predicate;
    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer lb = this.getLoadBalancer();
        List<Server> allServers = lb.getAllServers();
        List<Server> serverList = this.filterServers(allServers);
        return this.getPredicate().chooseRoundRobinAfterFiltering(serverList, key).orNull();
    }

    public abstract List<Server> filterServers(List<Server> serverList);
}
