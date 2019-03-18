package com.rep.organiza.organizarep.task.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.Util.FragmentManager;
import com.rep.organiza.organizarep.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TaskActivity extends BaseActivity {
    @Bind(R.id.container_task)
    FrameLayout containerFragment;

    @Bind(R.id.container_change_task)
    FrameLayout changeTaskFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);
        initFragment();
        //configureActionBar();
    }

    protected void initFragment() {
        String initialFrag = "frag_0";
        ListTasksFragment listTasksFragment = new ListTasksFragment();
        FragmentManager.replaceFragment(R.id.container_task, listTasksFragment, initialFrag,
                false, getSupportFragmentManager());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_action:
                Toast.makeText(this, "Adicionando tarefa", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void configureActionBar() {
        mActionBar = getSupportActionBar();
        if (mActionBar == null) {
            return;
        }

        //TODO ActionBar setting
        mActionBar.setDisplayShowCustomEnabled(true);
//        mActionBar.setCustomView(R.drawable.ic_add);
    }

}
