package com.rep.organiza.organizarep.task.presenter;

import com.rep.organiza.organizarep.base.BasePresenter;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.model.User;
import com.rep.organiza.organizarep.task.model.Status;
import com.rep.organiza.organizarep.task.model.UserSelect;
import com.rep.organiza.organizarep.task.model.WeekDay;
import com.rep.organiza.organizarep.task.view.ListTasksFragment;
import com.rep.organiza.organizarep.task.view.SelectUserFragment;

import java.util.ArrayList;
import java.util.List;

public class SelectUserPresenter extends BasePresenter {
    private SelectUserFragment fragment;
    private List<UserSelect> list;

    public SelectUserPresenter(SelectUserFragment fragment){
        this.fragment = fragment;
        list = new ArrayList<UserSelect>();

        for(int i=1; i<10; i++)
        mockData();
    }

    private void mockData() {
        UserSelect user  = new UserSelect("Lulinha", "", "", false);
        list.add(user);
    }

    public void loadUser(){
        fragment.showUsers(list);
    }
}
