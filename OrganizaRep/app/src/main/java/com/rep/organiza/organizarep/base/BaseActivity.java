package com.rep.organiza.organizarep.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rep.organiza.organizarep.Constants;
import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.helper.NetworkHelper;


public class BaseActivity extends AppCompatActivity {

    protected ActionBar mActionBar;
    private static int mForegroundActivities = 0;
    private boolean inBackground = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected void configureActionBar() {
        mActionBar = getSupportActionBar();
        if (mActionBar == null) {
            return;
        }

        //TODO ActionBar setting
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("");
        //mActionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_voltar));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mForegroundActivities++;

        if (mForegroundActivities == 1) {
            Log.i(Constants.ACTIVITY_CONTROL, "in foreground");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        inBackground = false;

        if (!NetworkHelper.isOnline(this)) {
            Snackbar.make(this.getWindow().getDecorView().getRootView(), R.string.network_not_connected, Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        inBackground = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        inBackground = true;
        mForegroundActivities--;

        if (mForegroundActivities == 0) {
            Log.i(Constants.ACTIVITY_CONTROL, "in background");
        }
    }

    public static boolean isAppInForeground() {
        return mForegroundActivities == 1;
    }

    public boolean isAlive() {
        return !inBackground && !isFinishing();
    }
}
