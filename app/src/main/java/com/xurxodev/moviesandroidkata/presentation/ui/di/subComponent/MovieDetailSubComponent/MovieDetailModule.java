package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.MovieDetailSubComponent;

import com.xurxodev.moviesandroidkata.presentation.presenter.MovieDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailModule {

    @Provides
    MovieDetailPresenter providesMovieDetailPresenter(MovieDetailPresenter.View movieDetailFragment) {
        return new MovieDetailPresenter(movieDetailFragment);
    }
}
