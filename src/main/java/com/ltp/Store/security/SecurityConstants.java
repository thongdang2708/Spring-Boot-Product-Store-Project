package com.ltp.Store.security;

public class SecurityConstants {
    public static final String registerPath = "/user/register";
    public static final int TOKEN_EXPIRATION = 7200000;
    public static final String SECRET_KEY = "adsmvn";
    public static final String registerAdminPath = "/user/register/admin";
    public static final String deleteUserPath = "/user/auth/{id}/delete";
}
