package com.xurxodev.moviesandroidkata.presentation.navigation;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class Navigate {

    private AppCompatActivity activity;

    public Navigate(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void addFragment(int container, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .add(container, fragment)
                .commit();
    }

    public void navigateToFragment(int container, Fragment currentFragment, Fragment newFragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .hide(currentFragment)
                .add(container, newFragment)
                .addToBackStack(null)
                .commit();
    }

    public void navigateToBack(Fragment currentFragment, Fragment oldFragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .hide(currentFragment)
                .show(oldFragment)
                .commit();
    }
}
