package com.insigma.springboot.service;


import com.insigma.springboot.entity.User;

import java.util.List;

/**
 * Created by lxg
 * on 2017/2/21.
 */
public interface UserService {

    User findByUsername(String name);

    List<User> findAll();

}
