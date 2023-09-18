package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.moviesSubComponent;

import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MoviesFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {MoviesModule.class})
public interface MoviesSubComponent {
    void inject(MoviesFragment moviesFragment);

    @Subcomponent.Factory
    interface Factory {
        MoviesSubComponent create(@BindsInstance MoviesFragment moviesFragment);
    }
}