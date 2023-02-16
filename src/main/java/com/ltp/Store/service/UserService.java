package com.ltp.Store.service;

import com.ltp.Store.entity.User;

public interface UserService {
    User getUser(Long id);

    User register(User user);

    User getUserByUsername(String username);

    User assignRoleToUser(Long userId, Long roleId);

    User registerAdmin(User user);

    void deleteUser(Long id);

    User updateUser(Long userId);
}
