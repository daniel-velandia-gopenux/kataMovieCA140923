package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.MovieDetailSubComponent;

import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.useCase.GetMovieDetailUseCase;
import com.xurxodev.moviesandroidkata.presentation.presenter.MovieDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailModule {

    @Provides
    GetMovieDetailUseCase providesGetMovieDetailUseCase(MainThread mainThread,
                                                        Executor backgroundExecutor,
                                                        MovieRepository movieRepository) {
        return new GetMovieDetailUseCase(mainThread, backgroundExecutor, movieRepository);
    }

    @Provides
    MovieDetailPresenter providesMovieDetailPresenter(GetMovieDetailUseCase getMovieDetailUseCase,
                                                      MovieDetailPresenter.View movieDetailFragment) {
        return new MovieDetailPresenter(getMovieDetailUseCase, movieDetailFragment);
    }
}
