package com.rep.organiza.organizarep.task.view;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rep.organiza.organizarep.Constants;
import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.Util.FragmentManager;
import com.rep.organiza.organizarep.base.BaseFragment;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.task.model.Status;
import com.rep.organiza.organizarep.task.model.WeekDay;
import com.rep.organiza.organizarep.task.model.SelectableWeekDay;
import com.rep.organiza.organizarep.task.presenter.CreateTaskPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateTaskFragment extends BaseFragment{

    private TaskActivity activity;
    private CreateTaskPresenter presenter;
    private Task task;

    @Bind(R.id.bt_next)
    Button btNext;

    @Bind(R.id.et_new_task_description)
    EditText edDescription;

    @Bind(R.id.et_new_task_title)
    EditText edTitle;

    @Bind(R.id.cv_sunday_circle)
    CircleImageView cvSunday;

    @Bind(R.id.constraintLayoutSunday)
    ConstraintLayout layoutSunday;

    @Bind(R.id.cv_monday_circle)
    CircleImageView cvMonday;

    @Bind(R.id.constraintLayoutMonday)
    ConstraintLayout layoutMonday;

    @Bind(R.id.cv_tuesday_circle)
    CircleImageView cvTuesday;

    @Bind(R.id.constraintLayoutTuesday)
    ConstraintLayout layoutTuesday;

    @Bind(R.id.cv_wednesday_circle)
    CircleImageView cvWednesday;

    @Bind(R.id.constraintLayoutWednesday)
    ConstraintLayout layoutWednesday;

    @Bind(R.id.cv_thursday_circle)
    CircleImageView cvThursday;

    @Bind(R.id.constraintLayoutThursday)
    ConstraintLayout layoutThursday;

    @Bind(R.id.cv_friday_circle)
    CircleImageView cvFriday;

    @Bind(R.id.constraintLayoutFriday)
    ConstraintLayout layoutFriday;

    @Bind(R.id.cv_saturday_circle)
    CircleImageView cvSaturday;

    @Bind(R.id.constraintLayoutSaturday)
    ConstraintLayout layoutSaturday;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        presenter = new CreateTaskPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_task, container, false);
        ButterKnife.bind(this, view);
        activity = (TaskActivity) this.getActivity();
        setNextButtonOnclick();
        presenter.loadTasks();
        initializeWeekDays();
        return view;
    }

    private void setNextButtonOnclick(){
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTasksAttributes();

                Bundle b = new Bundle();
                b.putSerializable(Constants.DATATRANSFERING_FROM_CREATETASK_TO_USERSELECT, task);

                SelectUserFragment fragment = new SelectUserFragment();
                fragment.setArguments(b);

                FragmentManager.replaceFragment(R.id.container_task, fragment, "f2", true, getFragmentManager());
            }
        });
    }

    private void setTasksAttributes() {
        List<WeekDay> days = presenter.getWeekDays();

        String description = edDescription.getText().toString();
        String title = edTitle.getText().toString();

        task = new Task(title, description, null , days);
    }

    private void setCircleImageViewOnclick(ConstraintLayout layout, CircleImageView ic, SelectableWeekDay day){
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = day.isSelected();
                isSelected = !isSelected;
                day.setSelected(isSelected);

                setCircleImageViewColor(ic, isSelected);
            }
        });
    }

    private void setCircleImageViewColor(CircleImageView ic, boolean isSelected) {
        if(isSelected){
            ic.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorToDoTask),  PorterDuff.Mode.SRC_IN);
        }else{
            ic.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorNotTaskDay),  PorterDuff.Mode.SRC_IN);
        }
    }

    public void initializeWeekDays(){
        setCircleImageViewColor(cvSunday, false);
        setCircleImageViewColor(cvMonday, false);
        setCircleImageViewColor(cvTuesday, false);
        setCircleImageViewColor(cvWednesday, false);
        setCircleImageViewColor(cvThursday, false);
        setCircleImageViewColor(cvFriday, false);
        setCircleImageViewColor(cvSaturday, false);
    }

    public void showDays(ArrayList<SelectableWeekDay> days) {
        setCircleImageViewOnclick(layoutSunday, cvSunday, days.get(0));
        setCircleImageViewOnclick(layoutMonday, cvMonday, days.get(1));
        setCircleImageViewOnclick(layoutTuesday, cvTuesday, days.get(2));
        setCircleImageViewOnclick(layoutWednesday, cvWednesday, days.get(3));
        setCircleImageViewOnclick(layoutThursday, cvThursday, days.get(4));
        setCircleImageViewOnclick(layoutFriday, cvFriday, days.get(5));
        setCircleImageViewOnclick(layoutSaturday, cvSaturday, days.get(6));
    }

}
