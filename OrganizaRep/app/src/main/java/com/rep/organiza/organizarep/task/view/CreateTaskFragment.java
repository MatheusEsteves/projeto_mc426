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

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.Util.FragmentManager;
import com.rep.organiza.organizarep.base.BaseFragment;
import com.rep.organiza.organizarep.task.model.WeekDay;
import com.rep.organiza.organizarep.task.model.WeekDaySelectable;
import com.rep.organiza.organizarep.task.presenter.CreateTaskPresenter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateTaskFragment extends BaseFragment{

    private TaskActivity activity;
    private CreateTaskPresenter presenter;
    private Button btNext;

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
        btNext = view.findViewById(R.id.bt_next);
        setNextButtonOnclick();
        presenter.loadTasks();
        initializeWeekDays();
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

    private void setCircleImageViewOnclick(ConstraintLayout layout, CircleImageView ic, WeekDaySelectable day){
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

    public void showDays(ArrayList<WeekDaySelectable> days) {
        setCircleImageViewOnclick(layoutSunday, cvSunday, days.get(0));
        setCircleImageViewOnclick(layoutMonday, cvMonday, days.get(1));
        setCircleImageViewOnclick(layoutTuesday, cvTuesday, days.get(2));
        setCircleImageViewOnclick(layoutWednesday, cvWednesday, days.get(3));
        setCircleImageViewOnclick(layoutThursday, cvThursday, days.get(4));
        setCircleImageViewOnclick(layoutFriday, cvFriday, days.get(5));
        setCircleImageViewOnclick(layoutSaturday, cvSaturday, days.get(6));

    }
}
