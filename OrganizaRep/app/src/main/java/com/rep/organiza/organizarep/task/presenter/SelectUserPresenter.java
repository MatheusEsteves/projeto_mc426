package com.rep.organiza.organizarep.task.presenter;

import com.rep.organiza.organizarep.base.BasePresenter;
import com.rep.organiza.organizarep.task.model.SelectableUser;
import com.rep.organiza.organizarep.task.view.SelectUserFragment;

import java.util.ArrayList;
import java.util.List;

public class SelectUserPresenter extends BasePresenter {
    private SelectUserFragment fragment;
    private List<SelectableUser> list;

    public SelectUserPresenter(SelectUserFragment fragment){
        this.fragment = fragment;
        list = new ArrayList<SelectableUser>();

        for(int i=1; i<10; i++)
        mockData(i);
    }

    private void mockData(int i) {
        SelectableUser user  = new SelectableUser("Lulinha "+i, "", ""+i, false);
        list.add(user);
    }

    public void loadUser(){
        fragment.showUsers(list);
    }
}
