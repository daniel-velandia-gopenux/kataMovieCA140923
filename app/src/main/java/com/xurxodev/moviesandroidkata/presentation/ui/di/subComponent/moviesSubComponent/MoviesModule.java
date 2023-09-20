package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.moviesSubComponent;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.useCase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    MoviesPresenter providesMoviesPresenter(GetMoviesUseCase getMoviesUseCase,
                                            MoviesPresenter.View moviesFragment) {
        return new MoviesPresenter(getMoviesUseCase, moviesFragment);
    }

    @Provides
    GetMoviesUseCase.Callback providesMoviesPresenterCallback(GetMoviesUseCase getMoviesUseCase,
                                                              MoviesPresenter.View moviesFragment) {
        return new MoviesPresenter(getMoviesUseCase, moviesFragment);
    }

    @Provides
    GetMoviesUseCase providesGetMoviesUseCase(MovieRepository movieRepository, MainThread mainThread,
                                              Executor backgroundExecutor,
                                              Lazy<GetMoviesUseCase.Callback> callback) {
        return new GetMoviesUseCase(mainThread, backgroundExecutor, callback, movieRepository);
    }

}
