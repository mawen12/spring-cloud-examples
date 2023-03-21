package com.mawen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 将刷新事件分离出去，如果写在 ConfigurationController，因为该类上注解了 @RefreshScope ，会将 ConfigurationController 重新构造。
 * 如果从 ConfigurationController 调用 event，那么会因为无法摧毁而锁死
 * <p>
 * org.springframework.cloud.context.scope.GenericScope#destroy()
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/18
 */
@RestController
public class RefreshController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/event")
    public String event() {
        applicationContext.publishEvent(new RefreshEvent(this,null,"Just for test"));
        return "send RefreshEvent";
    }
}
