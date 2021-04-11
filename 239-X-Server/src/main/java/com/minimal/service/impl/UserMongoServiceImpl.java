package com.minimal.service.impl;

import com.minimal.entity.mongo.User;
import com.minimal.repository.UserMongoRepository;
import com.minimal.service.UserMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMongoServiceImpl implements UserMongoService {
    @Autowired
    private UserMongoRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return this.findByName(name);
    }
}
