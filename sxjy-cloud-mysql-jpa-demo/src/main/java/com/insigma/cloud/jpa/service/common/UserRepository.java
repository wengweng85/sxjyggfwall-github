package com.insigma.cloud.jpa.service.common;

import com.insigma.cloud.jpa.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wengsh on 2019/2/27.
 */
public interface UserRepository extends JpaRepository<SysUser,String> {
    List<SysUser> findByUsername(String userName);
    List<SysUser> findByNameLike(String name);
}

