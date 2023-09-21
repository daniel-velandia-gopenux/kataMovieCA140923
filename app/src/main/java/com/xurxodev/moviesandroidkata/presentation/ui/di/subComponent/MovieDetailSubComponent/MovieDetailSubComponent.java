package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.MovieDetailSubComponent;

import com.xurxodev.moviesandroidkata.presentation.presenter.MovieDetailPresenter;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MovieDetailFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {MovieDetailModule.class})
public interface MovieDetailSubComponent {

    void inject(MovieDetailFragment movieDetailFragment);

    @Subcomponent.Factory
    interface Factory {
        MovieDetailSubComponent create(@BindsInstance MovieDetailPresenter.View movieDetailFragment);
    }
}
