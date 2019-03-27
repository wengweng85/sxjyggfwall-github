package com.insigma.springboot.mapper;

import com.insigma.springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
