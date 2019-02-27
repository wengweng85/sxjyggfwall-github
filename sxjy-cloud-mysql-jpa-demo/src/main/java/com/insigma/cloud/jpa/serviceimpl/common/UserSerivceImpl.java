package com.insigma.cloud.jpa.serviceimpl.common;


import com.insigma.cloud.jpa.entity.SysUser;
import com.insigma.cloud.jpa.service.common.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * userSerivce
 */
@Service("userSerivce")
public class UserSerivceImpl {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void addMoreUsers(){
        SysUser user1 = new SysUser();
        user1.setUsername("123");
        user1.setName("123");
        user1.setPassword("123");
        userRepository.save(user1);
        SysUser user2 = new SysUser();
        user2.setUsername("234");
        user2.setName("123");
        user2.setPassword("123");
        userRepository.save(user2);
    }

    public void addMoreList(){
        List<SysUser> userList = new ArrayList<SysUser>();
        SysUser user1 = new SysUser();
        user1.setUsername("345");
        user1.setName("123");
        user1.setPassword("123");
        userList.add(user1);
        SysUser user2 = new SysUser();
        user2.setUsername("456");
        user2.setName("123");
        user2.setPassword("123");
        userList.add(user2);
        userRepository.saveAll(userList);
    }


}
