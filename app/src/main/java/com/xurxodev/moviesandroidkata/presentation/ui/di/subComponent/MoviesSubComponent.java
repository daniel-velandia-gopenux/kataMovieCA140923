package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent;

import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MoviesFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {DomainModule.class, PicassoModule.class})
public interface MoviesSubComponent {
    void inject(MoviesFragment moviesFragment);

    @Subcomponent.Factory
    interface Factory {
        MoviesSubComponent create(@BindsInstance MoviesFragment moviesFragment);
    }
}
