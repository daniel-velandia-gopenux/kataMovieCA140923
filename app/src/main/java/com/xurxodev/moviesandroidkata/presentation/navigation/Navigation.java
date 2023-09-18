package com.xurxodev.moviesandroidkata.presentation.navigation;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class Navigation {

    private AppCompatActivity activity;
    private int containerId;

    public Navigation(AppCompatActivity activity, int containerId) {
        this.activity = activity;
        this.containerId = containerId;
    }

    public void addFragment(Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .add(containerId, fragment)
                .commit();
    }

    public void navigateToFragment(Fragment actualFragment, Fragment newFragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .hide(actualFragment)
                .add(containerId, newFragment)
                .addToBackStack(null)
                .commit();
    }

    public void navigateToBack(Fragment oldFragment, Fragment actualFragment) {
        if(oldFragment.isHidden()) {
            activity.getSupportFragmentManager().beginTransaction()
                    .hide(actualFragment)
                    .show(oldFragment)
                    .commit();
        }
    }
}
