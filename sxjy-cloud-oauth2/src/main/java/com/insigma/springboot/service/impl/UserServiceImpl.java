package com.insigma.springboot.service.impl;

import com.insigma.springboot.entity.User;
import com.insigma.springboot.mapper.UserRepository;
import com.insigma.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxg
 * on 2017/2/21.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
