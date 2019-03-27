package com.insigma.springboot.service.impl;

import com.insigma.springboot.mapper.UserRoleRepository;
import com.insigma.springboot.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxg
 * on 2017/2/21.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public List<String> findRoles(int uid) {

        List<String> list = userRoleRepository.findRoleName(uid);

        return list;
    }
}
