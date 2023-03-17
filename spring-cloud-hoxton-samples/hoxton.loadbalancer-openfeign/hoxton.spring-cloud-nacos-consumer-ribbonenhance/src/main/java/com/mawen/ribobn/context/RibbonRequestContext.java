package com.mawen.ribobn.context;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Ribbon 请求上下文
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/15
 */
public class RibbonRequestContext {

    private final Map<String, String> attr = new HashMap<>();

    public String put(String key, String value) {
        return attr.put(key, value);
    }

    public String remove(String key) {
        return attr.remove(key);
    }

    public String get(String key) {
        return attr.get(key);
    }

}
