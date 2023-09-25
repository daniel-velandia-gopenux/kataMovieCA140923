package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.moviesSubComponent;

import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.ui.activity.MoviesActivity;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MoviesFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {MoviesModule.class})
public interface MoviesSubComponent {
    void inject(MoviesFragment moviesFragment);

    @Subcomponent.Factory
    interface Factory {
        MoviesSubComponent create(@BindsInstance MoviesPresenter.View moviesFragment,
                                  @BindsInstance MoviesActivity moviesActivity);
    }
}
