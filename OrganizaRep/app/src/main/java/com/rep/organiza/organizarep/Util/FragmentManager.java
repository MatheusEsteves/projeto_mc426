package com.rep.organiza.organizarep.Util;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class FragmentManager {

    public static void replaceFragment(@IdRes int container, Fragment fragment,
                                       String label, boolean toBack, android.support.v4.app.FragmentManager fragmentManager) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.replace(container, fragment, label);

        if (toBack) {
            transaction.addToBackStack(label);
        }

        transaction.commit();
    }

    public static int getBackStackCount(android.support.v4.app.FragmentManager fragmentManager) {
        return fragmentManager.getBackStackEntryCount();
    }

    public static void popToFragment(android.support.v4.app.FragmentManager fragmentManager, String fragmentTag) {
        fragmentManager.popBackStack(fragmentTag, 0);
    }

    public static boolean contains(String fragmentTag, android.support.v4.app.FragmentManager supportFragmentManager) {
        return supportFragmentManager.findFragmentByTag(fragmentTag) != null;
    }
}
