package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.moviesSubComponent;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.useCase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.presentation.controller.MoviesController;
import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MoviesFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    MoviesPresenter providesMoviesPresenter(MoviesFragment moviesFragment) {
        return new MoviesPresenter(moviesFragment);
    }

    @Provides
    GetMoviesUseCase.Callback providesMoviesPresenterCallback(MoviesFragment moviesFragment) {
        return new MoviesPresenter(moviesFragment);
    }

    @Provides
    GetMoviesUseCase providesGetMoviesUseCase(GetMoviesUseCase.Callback moviesPresenter,
                                              MovieRepository movieRepository, MainThread mainThread,
                                              Executor backgroundExecutor) {
        return new GetMoviesUseCase(moviesPresenter, movieRepository, mainThread, backgroundExecutor);
    }

    @Provides
    MoviesController providesMoviesController(GetMoviesUseCase getMoviesUseCase) {
        return new MoviesController(getMoviesUseCase);
    }

}
