package com.xurxodev.moviesandroidkata.presentation.ui.di.component;

import android.app.Application;

import com.xurxodev.moviesandroidkata.presentation.ui.di.SubComponents;
import com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.MoviesSubComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules={DataModule.class, NetModule.class, SubComponents.class})
public interface MoviesComponent {

    MoviesSubComponent.Factory getMovieSubComponent();

    @Component.Factory
    interface Factory {
        MoviesComponent create(@BindsInstance Application application);
    }
}