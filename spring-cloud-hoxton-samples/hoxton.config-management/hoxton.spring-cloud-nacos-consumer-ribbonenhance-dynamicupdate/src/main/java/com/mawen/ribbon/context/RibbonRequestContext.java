package com.mawen.ribbon.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/19
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
