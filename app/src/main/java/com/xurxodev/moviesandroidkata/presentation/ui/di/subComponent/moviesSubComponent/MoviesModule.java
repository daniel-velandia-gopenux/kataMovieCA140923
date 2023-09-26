package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.moviesSubComponent;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.useCase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.presentation.navigation.NavigatorImpl;
import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.presenter.boundary.Navigator;
import com.xurxodev.moviesandroidkata.presentation.ui.activity.MoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    MoviesPresenter providesMoviesPresenter(GetMoviesUseCase getMoviesUseCase,
                                            MoviesPresenter.View moviesFragment,
                                            Navigator navigator) {
        return new MoviesPresenter(getMoviesUseCase, moviesFragment, navigator);
    }

    @Provides
    GetMoviesUseCase providesGetMoviesUseCase(MovieRepository movieRepository, MainThread mainThread,
                                              Executor backgroundExecutor) {
        return new GetMoviesUseCase(mainThread, backgroundExecutor, movieRepository);
    }

    @Provides
    Navigator providesNavigator(MoviesActivity moviesActivity) {
        return new NavigatorImpl(moviesActivity);
    }

}
