package com.mawen.user.dao;

import com.mawen.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/23
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByIdAndName(Long id, String name);
}
