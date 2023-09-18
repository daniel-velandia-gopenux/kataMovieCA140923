package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.movieDetailSubComponent;

import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MovieDetailFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {MovieDetailModule.class})
public interface MovieDetailSubComponent {

    void inject(MovieDetailFragment movieDetailFragment);

    @Subcomponent.Factory
    interface Factory {
        MovieDetailSubComponent create(@BindsInstance MovieDetailFragment movieDetailFragment);
    }
}
