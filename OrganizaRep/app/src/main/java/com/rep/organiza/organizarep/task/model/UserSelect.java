package com.rep.organiza.organizarep.task.model;

import com.rep.organiza.organizarep.model.User;

public class UserSelect extends User {

    private boolean isSelested;

    public UserSelect(String userName, String userImagePath, String userEmail) {
        super(userName, userImagePath, userEmail);
    }

    public boolean isSelested() {
        return isSelested;
    }

    public void setSelested(boolean selested) {
        isSelested = selested;
    }
}
