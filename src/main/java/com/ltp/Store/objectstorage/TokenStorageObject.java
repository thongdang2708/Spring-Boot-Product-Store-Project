package com.ltp.Store.objectstorage;

public class TokenStorageObject {

    private String username;
    private String token;

    public TokenStorageObject(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public TokenStorageObject() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
