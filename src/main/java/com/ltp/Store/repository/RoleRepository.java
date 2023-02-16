package com.ltp.Store.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ltp.Store.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByDescription(String description);
}
