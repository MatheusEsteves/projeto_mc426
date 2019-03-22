package com.rep.organiza.organizarep.task.model;

import com.rep.organiza.organizarep.model.User;

public class SelectableUser extends User {

    private boolean isSelected;

    public SelectableUser(String userName, String userImagePath, String userEmail, boolean isSelected) {
        super(userName, userImagePath, userEmail);
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
