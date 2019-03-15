package com.rep.organiza.organizarep.model;

public class User {
    private String userImagePath;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public User(String userName, String userImagePath) {
        this.userImagePath = userImagePath;
        this.userName = userName;
    }
}
