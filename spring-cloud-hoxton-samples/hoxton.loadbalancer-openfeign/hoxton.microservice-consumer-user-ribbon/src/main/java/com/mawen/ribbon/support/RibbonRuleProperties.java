package com.mawen.ribbon.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/9
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = RibbonRuleProperties.PROPERTIES_PREFIX)
public class RibbonRuleProperties {

    public static final String PROPERTIES_PREFIX = "mawen.ribbon.rule";

    private boolean enabled = false;

    private String tag;

    private List<String> priorIpPattern = new ArrayList<>();


}
