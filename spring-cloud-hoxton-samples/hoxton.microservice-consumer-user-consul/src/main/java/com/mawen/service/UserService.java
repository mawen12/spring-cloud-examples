package com.mawen.service;

import com.mawen.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/8
 */
@FeignClient(value = "provider-consul")
public interface UserService {

    @GetMapping("/{id}")
    User getById(@PathVariable("id") long id);
}
