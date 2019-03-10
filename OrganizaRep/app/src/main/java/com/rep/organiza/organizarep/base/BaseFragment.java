package com.rep.organiza.organizarep.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.rep.organiza.organizarep.Listener.OnChangeFragmentListener;

public class BaseFragment extends Fragment {

    private OnChangeFragmentListener mOnChangeFragmentListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnChangeFragmentListener) {
            mOnChangeFragmentListener = (OnChangeFragmentListener) context;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setFragmentOnMenu(int menuId) {
        if (mOnChangeFragmentListener != null) mOnChangeFragmentListener.setFragmentOnMenu(menuId);
    }

    public void setMenuSelected(int menuId) {
        if (mOnChangeFragmentListener != null) {
            mOnChangeFragmentListener.setMenuSelected(menuId);
        }

    }

    public boolean isAlive() {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        return isVisible() && !isRemoving() && baseActivity != null && baseActivity.isAlive();
    }

}
