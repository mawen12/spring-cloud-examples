package com.mawen.ribbon.predicate;

import com.mawen.ribbon.support.RibbonRuleProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/9
 */
public class MetadataAwarePredicate extends DiscoveryEnabledPredicate {
    public static final MetadataAwarePredicate INSTANCE = new MetadataAwarePredicate();

    @Autowired
    private RibbonRuleProperties properties;

    @Override
    protected boolean apply(MetadataServer server) {
        String localTag = properties.getTag();
        if (StringUtils.isBlank(localTag)) {
            return true;
        } else {
            Map<String, String> metadata = server.getMetadata();
            String metadataTag = metadata.get("tag");
            return !StringUtils.isBlank(metadataTag) && metadataTag.equals(localTag);
        }
    }
}
