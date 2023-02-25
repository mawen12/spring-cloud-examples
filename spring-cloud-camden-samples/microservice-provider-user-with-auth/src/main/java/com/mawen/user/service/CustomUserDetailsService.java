package com.mawen.user.service;

import com.mawen.user.entity.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/25
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * 模拟两个账号：
     * <p>
     * 1.账号是user，密码是password1，角色是user-role
     * <p>
     * 2.账号是admin，密码是password2，角色是admin-role
     *
     * @param username 账户
     * @return 用户信息
     * @throws UsernameNotFoundException 用户名不存在时抛出的异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return new SecurityUser(username, "password1", "user-role");
        } else if ("admin".equals(username)) {
            return new SecurityUser(username, "password2", "admin-role");
        }

        return null;
    }

}
