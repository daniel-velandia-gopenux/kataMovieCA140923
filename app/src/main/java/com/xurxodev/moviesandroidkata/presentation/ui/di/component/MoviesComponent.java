package com.xurxodev.moviesandroidkata.presentation.ui.di.component;

import android.app.Application;

import com.xurxodev.moviesandroidkata.presentation.ui.di.SubComponents;
import com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.movieDetailSubComponent.MovieDetailSubComponent;
import com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.moviesSubComponent.MoviesSubComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules={DataModule.class, NetModule.class, PicassoModule.class, ThreadModule.class, SubComponents.class})
public interface MoviesComponent {

    MoviesSubComponent.Factory getMovieSubComponent();

    MovieDetailSubComponent.Factory getMovieDetailSubComponent();

    @Component.Factory
    interface Factory {
        MoviesComponent create(@BindsInstance Application application);
    }
}