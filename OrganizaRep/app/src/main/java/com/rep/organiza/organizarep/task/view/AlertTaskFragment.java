package com.rep.organiza.organizarep.task.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.Util.FragmentManager;
import com.rep.organiza.organizarep.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AlertTaskFragment extends BaseFragment {

    private TaskActivity taskActivity;

    @Bind(R.id.tv_alert_task_text)
    TextView tvAlertTaskText;

    @Bind(R.id.tv_cancel_alert_task_action)
    TextView tvCancelAlertTaskAction;

    @Bind(R.id.tv_alert_task_action)
    TextView tvAlertTaskAction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_alert_task, container, false);
        ButterKnife.bind(this, view);
        this.taskActivity = (TaskActivity)this.getActivity();
        this.setCancelAlertTaskOnClick();
        this.setAlertTaskOnClick();
        return view;
    }

    private void goBackToTaskList(){
        FragmentManager.removeFragment(this, taskActivity.getSupportFragmentManager());
    }

    private void setCancelAlertTaskOnClick(){
        tvCancelAlertTaskAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToTaskList();
            }
        });
    }

    private void setAlertTaskOnClick(){
        tvAlertTaskAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),
                        "Uma notificação foi enviada!",
                        Toast.LENGTH_LONG
                ).show();
                goBackToTaskList();
            }
        });
    }
}
