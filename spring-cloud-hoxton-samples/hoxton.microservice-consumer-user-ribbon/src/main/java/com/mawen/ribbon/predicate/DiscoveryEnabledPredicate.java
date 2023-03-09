package com.mawen.ribbon.predicate;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/9
 */
public abstract class DiscoveryEnabledPredicate extends AbstractServerPredicate {

    @Override
    public boolean apply(@Nullable PredicateKey input) {
        if (input == null) {
            return false;
        } else {
            MetadataServer metadataServer = new MetadataServer(input.getServer());
            return metadataServer.hasMetadata() ? this.apply(metadataServer) : false;
        }
    }

    protected abstract boolean apply(MetadataServer server);
}
