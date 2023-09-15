package com.xurxodev.moviesandroidkata;

import android.app.Application;

import com.xurxodev.moviesandroidkata.presentation.ui.di.component.DaggerMoviesComponent;
import com.xurxodev.moviesandroidkata.presentation.ui.di.component.MoviesComponent;

public class MoviesApplication extends Application {
    private MoviesComponent moviesComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        moviesComponent = DaggerMoviesComponent.factory().create(this);
    }

    public MoviesComponent getMoviesComponent() {
        return moviesComponent;
    }
}
