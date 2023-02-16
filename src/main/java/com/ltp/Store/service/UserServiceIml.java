package com.ltp.Store.service;

import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ltp.Store.entity.Role;
import com.ltp.Store.entity.User;
import com.ltp.Store.exception.RoleNotFoundWithDescriptionException;
import com.ltp.Store.exception.UserNotFoundWithIdException;
import com.ltp.Store.exception.UserNotFoundWithUsername;
import com.ltp.Store.exception.UsernameExistsException;
import com.ltp.Store.repository.RoleRepository;
import com.ltp.Store.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    HttpServletResponse httpServletResponse;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundWithIdException(id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundWithUsername(username);
        }
    }

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        User user = getUser(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if (role.isPresent()) {
            Role unwrappedRole = role.get();
            user.addRoles(unwrappedRole);
            return userRepository.save(user);
        } else {
            throw new RoleNotFoundWithDescriptionException("This user does not exist!");
        }
    }

    @Override
    public User register(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UsernameExistsException("this username exists already!");
        } else {

            Role role = checkRole(roleRepository.findByDescription("user"));

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User createdUser = userRepository.save(user);

            assignRoleToUser(createdUser.getId(), role.getId());

            return createdUser;

        }
    }

    public Role checkRole(Optional<Role> entity) {

        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new RoleNotFoundWithDescriptionException("This role does not exist in a database!");
        }
    }

    @Override
    public User registerAdmin(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UsernameExistsException("This username exists already!");
        } else {

            Role role = checkRole(roleRepository.findByDescription("admin"));

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User createdAdminuser = userRepository.save(user);

            assignAdminRoleToUser(createdAdminuser.getId(), role.getId());

            return createdAdminuser;

        }
    }

    public void assignAdminRoleToUser(Long userId, Long roleId) {

        User user = getUser(userId);

        Optional<Role> role = roleRepository.findById(roleId);

        if (role.isPresent()) {
            Role unwrappedRole = role.get();
            user.addRoles(unwrappedRole);
            userRepository.save(user);
        } else {
            throw new RoleNotFoundWithDescriptionException("Role not found!");
        }

    }

    @Override
    public User updateUser(Long userId) {
        User user = getUser(userId);

        Role adminRole = checkRole(roleRepository.findByDescription("admin"));

        if (user.getRoles().contains(adminRole) == false) {
            user.addRoles(adminRole);
        }

        return userRepository.save(user);

    }
}
