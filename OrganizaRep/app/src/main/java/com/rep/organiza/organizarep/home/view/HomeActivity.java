package com.rep.organiza.organizarep.home.view;

import android.os.Bundle;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.base.BaseActivity;

import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }
}
