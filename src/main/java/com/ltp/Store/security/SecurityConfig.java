package com.ltp.Store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.ltp.Store.security.filter.AuthenticationFilter;
import com.ltp.Store.security.filter.ExceptionHandlerFilter;
import com.ltp.Store.security.filter.JWTAuthorizationFilter;
import com.ltp.Store.security.handler.CustomLogoutHandler;
import com.ltp.Store.security.manager.CustomAuthenticationManager;
import com.ltp.Store.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomLogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, userService);
        authenticationFilter.setFilterProcessesUrl("/login");

        http.httpBasic()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, SecurityConstants.registerPath).permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/*/role/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE, SecurityConstants.deleteUserPath).hasAuthority("admin")
                        .requestMatchers("/user/auth/**").hasAnyAuthority("user", "admin")
                        .requestMatchers(HttpMethod.GET, "/product/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/sort/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/offset/*/pageSize/*").permitAll()
                        .requestMatchers("/product/**").hasAuthority("admin")
                        .requestMatchers(HttpMethod.GET, "/product/productsearch/*").permitAll()
                        .requestMatchers("/order/**").hasAnyAuthority("user", "admin")
                        .requestMatchers(HttpMethod.DELETE, "/order/**").hasAuthority("admin")
                        .requestMatchers(HttpMethod.POST, SecurityConstants.registerAdminPath).permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/*/updateAdmin").hasAuthority("admin")
                        .anyRequest().authenticated());

        http
                .logout()
                .addLogoutHandler((request, response, auth) -> {
                    SecurityContextHolder.clearContext();
                    response.setHeader("Authorization", "");
                })
                .deleteCookies("token")
                .invalidateHttpSession(true)
                .permitAll();

        return http.build();
    }
}
