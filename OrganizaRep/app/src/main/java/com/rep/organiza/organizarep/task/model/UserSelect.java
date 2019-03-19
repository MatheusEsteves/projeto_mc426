package com.rep.organiza.organizarep.task.model;

import com.rep.organiza.organizarep.model.User;

/**
 * Created by lumag on 18/03/2019.
 */

public class UserSelect extends User {

    private boolean isSelested;

    public UserSelect(String userName, String userImagePath) {
        super(userName, userImagePath);
    }

    public boolean isSelested() {
        return isSelested;
    }

    public void setSelested(boolean selested) {
        isSelested = selested;
    }
}
