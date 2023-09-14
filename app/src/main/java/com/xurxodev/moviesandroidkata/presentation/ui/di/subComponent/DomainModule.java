package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent;

import com.xurxodev.moviesandroidkata.domain.executor.impl.ThreadExecutor;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.presentation.presenter.impl.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MoviesFragment;
import com.xurxodev.moviesandroidkata.threading.MainThreadImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {

    @Provides
    MoviesPresenter providesMoviePresenter(MoviesFragment moviesFragment,
                                           MovieRepository movieRepository) {
        return new MoviesPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), moviesFragment, movieRepository);
    }

}
