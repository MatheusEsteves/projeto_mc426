package com.rep.organiza.organizarep.task.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.Util.FragmentManager;
import com.rep.organiza.organizarep.base.BaseFragment;

import butterknife.ButterKnife;

public class CreateTaskFragment extends BaseFragment{

    private TaskActivity activity;
    private Button btNext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_task, container, false);
        ButterKnife.bind(this, view);
        activity = (TaskActivity) this.getActivity();

        btNext = view.findViewById(R.id.bt_next);
        setNextButtonOnclick();

        return view;
    }

    private void setNextButtonOnclick(){
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager.replaceFragment(R.id.container_task, new ListTasksFragment(), "f2", true, getFragmentManager());
            }
        });
    }
}
