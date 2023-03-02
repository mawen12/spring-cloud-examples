package com.mawen.movie.feignclient;

import com.mawen.movie.pojo.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * {@link UserFeignClient} 的 fallbackFactory 类，该类需实现 {@link FallbackFactory} 接口，并覆写 create 方法
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/2
 */
@Slf4j
@Component
public class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public User findById(long id) {
                // 日志最好放在各个fallback方法中，而不要直接放在create方法中
                // 否则在引用启动时，就会打印该日志
                log.info("fallback; reason was: ", cause);
                User user = new User();
                user.setId(-1L);
                user.setUsername("默认用户");
                return user;
            }
        };
    }
}
