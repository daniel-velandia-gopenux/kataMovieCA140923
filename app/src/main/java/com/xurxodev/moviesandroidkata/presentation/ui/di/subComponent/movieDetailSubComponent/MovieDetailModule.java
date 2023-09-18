package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.movieDetailSubComponent;

import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.useCase.GetMovieDetailUseCase;
import com.xurxodev.moviesandroidkata.presentation.controller.MovieDetailController;
import com.xurxodev.moviesandroidkata.presentation.presenter.MovieDetailPresenter;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MovieDetailFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailModule {

    @Provides
    MovieDetailPresenter providesMovieDetailPresenter(MovieDetailFragment movieDetailFragment) {
        return new MovieDetailPresenter(movieDetailFragment);
    }

    @Provides
    GetMovieDetailUseCase.Callback providesMovieDetailPresenterCallback(
            MovieDetailFragment movieDetailFragment) {
        return new MovieDetailPresenter(movieDetailFragment);
    }

    @Provides
    GetMovieDetailUseCase providesGetMovieDetailUseCase(
            GetMovieDetailUseCase.Callback movieDetailPresenter,
            MovieRepository movieRepository, MainThread mainThread,
            Executor backgroundExecutor) {
        return new GetMovieDetailUseCase(movieDetailPresenter, movieRepository, mainThread, backgroundExecutor);
    }

    @Provides
    MovieDetailController providesMovieDetailController(GetMovieDetailUseCase getMovieDetailUseCase) {
        return new MovieDetailController(getMovieDetailUseCase);
    }
}
