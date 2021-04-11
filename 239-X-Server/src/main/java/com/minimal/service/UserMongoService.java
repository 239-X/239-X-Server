package com.minimal.service;

import com.minimal.entity.mongo.User;
import org.springframework.stereotype.Repository;

/**
 * @author administrator
 */
@Repository
public interface UserMongoService {
    /**
     * 保存用户
     * @param user
     */
    void save(User user);

    /**
     * 通过name查询用户
     * @param name
     * @return
     */
    User findByName(String name);
}
