package com.minimal.repository;
import com.minimal.entity.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author linzhiqiang
 */
public interface UserMongoRepository extends MongoRepository<User, String> {
    /**
     * 通过name查找用户
     * @param name
     * @return
     */

    User findByName(String name);
}