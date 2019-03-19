package com.rep.organiza.organizarep.model;

import java.io.Serializable;

public class User implements Serializable {
    private String userImagePath;
    private String userName;
    private String userEmail;

    public User(String userName, String userImagePath, String userEmail) {
        this.userImagePath = userImagePath;
        this.userName = userName;
        this.userEmail = userEmail;
    }

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

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getUserEmail(){
        return this.userEmail;
    }

    @Override
    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        if (other instanceof User){
            User otherUser = (User)other;
            if (this.userEmail == null && otherUser.userEmail == null){
                return true;
            }
            if (this.userEmail == null && otherUser.userEmail != null){
                return false;
            }
            if (this.userEmail != null && otherUser.userEmail == null){
                return false;
            }
            return this.userEmail.equals(otherUser.userEmail);
        }
        return false;
    }
}
