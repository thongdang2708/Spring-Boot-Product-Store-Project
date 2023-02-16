package com.ltp.Store.objectstorage;

public class UserStorageObject {

    private Long id;
    private String username;

    public UserStorageObject(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserStorageObject() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
