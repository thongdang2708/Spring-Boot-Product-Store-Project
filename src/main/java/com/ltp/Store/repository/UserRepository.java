package com.ltp.Store.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ltp.Store.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsUserByUsername(String username);

    Optional<User> findByUsername(String username);
}
