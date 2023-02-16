package com.ltp.Store.security.manager;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ltp.Store.entity.Role;
import com.ltp.Store.entity.User;
import com.ltp.Store.service.UserService;
import java.util.List;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.getUserByUsername(authentication.getName());

        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("You filled a wrong password! Please fill again!");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().stream().forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getDescription()));
        });

        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorities);
    }
}
