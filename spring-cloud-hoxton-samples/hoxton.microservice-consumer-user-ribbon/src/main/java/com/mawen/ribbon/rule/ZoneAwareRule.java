package com.mawen.ribbon.rule;

import com.mawen.ribbon.predicate.MetadataAwarePredicate;
import com.netflix.loadbalancer.PredicateKey;
import org.apache.commons.lang.ObjectUtils;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/9
 */
public class ZoneAwareRule extends MetadataAwareRule {

    public ZoneAwareRule() {
        super(new DefaultDiscoveryEnabledPredicate());
    }

    public static class DefaultDiscoveryEnabledPredicate extends MetadataAwarePredicate {

        @Override
        public boolean apply(@Nullable PredicateKey predicateKey) {
            if (predicateKey == null || predicateKey.getLoadBalancerKey() == null || "default".equalsIgnoreCase(String.valueOf(predicateKey.getLoadBalancerKey()))) {
                return super.apply(predicateKey);
            }
            return predicateKey.getServer() != null && ObjectUtils.equals(predicateKey.getServer().getZone(), predicateKey.getLoadBalancerKey());
        }
    }

}
