package com.insigma.cloud.jpa.service.common;

import com.insigma.cloud.jpa.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.List;

/**
 * Created by wengsh on 2019/2/27.
 */
public interface UserRepository extends JpaRepository<SysUser,String> {
    List<SysUser> findByUsername(String userName);
    List<SysUser> findByNameLike(String name);

    @Query(value = "select userid,name,username from SysUser b where b.name like %:name%")
    List<SysUser> findByNameMatch(@Param("name") String name);

    @Query(value = "select * from sys_user b where b.cnname like %?1%", nativeQuery = true)
    List<Book> findByName(String name);
}

