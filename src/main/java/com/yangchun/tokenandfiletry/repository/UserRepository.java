package com.yangchun.tokenandfiletry.repository;

import com.yangchun.tokenandfiletry.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tianyi
 * @date 2018-02-14 12:56
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
}
