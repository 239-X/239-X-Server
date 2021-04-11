package com.minimal.controller.test;

import com.minimal.entity.mongo.User;
import com.minimal.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linzhiqiang
 * @date 2019/3/4
 */
@RestController
public class MongodbTestController {

    @Autowired
    private UserMongoRepository repository;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public void test(){
        repository.deleteAll();
        repository.save(new User(1,"name1",19));
        repository.save(new User(2,"name2",20));
        System.out.println("User found with findAll():");
        System.out.println("-------------------------------");
        for (User user : repository.findAll()) {
            System.out.println(user);
        }
        System.out.println("------------------------");
        System.out.println("User found with findByName('name1'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByName("name1"));
    }
}
