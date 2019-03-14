package com.rep.organiza.organizarep.task.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.Util.FragmentManager;
import com.rep.organiza.organizarep.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TaskActivity extends BaseActivity {
    @Bind(R.id.container_task)
    FrameLayout containerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);
        initFragment();
    }

    protected void initFragment() {
        String initialFrag = "frag_0";

        ListTasksFragment listTasksFragment = new ListTasksFragment();
        FragmentManager.replaceFragment(R.id.container_task, listTasksFragment, initialFrag,
                false, getSupportFragmentManager());
    }

}
