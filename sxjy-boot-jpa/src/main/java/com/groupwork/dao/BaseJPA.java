package com.groupwork.dao;

/**
 * Created by wengsh on 2019/5/31.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 核心JPA
 * ========================
 */
@NoRepositoryBean
public interface BaseJPA<T>
        extends JpaRepository<T,Long>,
        JpaSpecificationExecutor<T>,
        QueryDslPredicateExecutor<T>
{
}