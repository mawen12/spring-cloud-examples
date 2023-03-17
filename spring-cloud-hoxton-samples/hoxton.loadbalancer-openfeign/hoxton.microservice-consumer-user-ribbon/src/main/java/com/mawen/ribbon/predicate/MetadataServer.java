package com.mawen.ribbon.predicate;

import com.netflix.loadbalancer.Server;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/9
 */
public class MetadataServer {

    private static final ConcurrentMap<Class<?>, Method> METHOD_MAP = new ConcurrentHashMap(1);

    private static final List<Class<?>> NO_METHOD_LIST = new ArrayList<>();

    private final Server server;

    private final Method method;

    public MetadataServer(Server server) {
        this.server = server;
        this.method = findMetadataMethod(server.getClass());
    }

    private static Method findMetadataMethod(Class<?> serverClass) {
        Method method = METHOD_MAP.get(serverClass);
        if (method != null) {
            return method;
        } else if (NO_METHOD_LIST.contains(serverClass)) {
            return null;
        } else {
            method = ReflectionUtils.findMethod(serverClass, "getMetadata");
            if (method == null) {
                NO_METHOD_LIST.add(serverClass);
            } else {
                METHOD_MAP.put(serverClass, method);
            }
        }

        return method;
    }

    public boolean hasMetadata() {
        return this.method != null;
    }

    public Map<String, String> getMetadata() {
        return (Map<String, String>)ReflectionUtils.invokeMethod(this.method, this.server);
    }
}
