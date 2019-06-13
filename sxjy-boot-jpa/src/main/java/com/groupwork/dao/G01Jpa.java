package com.groupwork.dao;

import com.groupwork.entity.G01;
import org.springframework.data.repository.CrudRepository;

public interface G01Jpa extends BaseJPA<G01> {
    /**通过username查找用户信息;*/
     G01 findByG0100(String g0100);
}