package com.mawen.movie.feignclient;

import com.mawen.movie.pojo.User;
import org.springframework.stereotype.Component;

/**
 * {@link UserFeignClient} 的回退类
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/2
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public User findById(long id) {
        User user = new User();
        user.setId(-1L);
        user.setUsername("默认用户");
        return user;
    }
}
