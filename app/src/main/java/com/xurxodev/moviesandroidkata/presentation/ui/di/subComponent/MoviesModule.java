package com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.executor.impl.ThreadExecutor;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.interactor.MoviesInteractor;
import com.xurxodev.moviesandroidkata.domain.interactor.base.AbstracInteractor;
import com.xurxodev.moviesandroidkata.domain.interactor.impl.MoviesInteractorImpl;
import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.presenter.impl.MoviesPresenterImpl;
import com.xurxodev.moviesandroidkata.presentation.ui.di.scope.MoviesScope;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MoviesFragment;
import com.xurxodev.moviesandroidkata.threading.MainThreadImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    @MoviesScope
    MoviesPresenter providesMoviesPresenter(Executor threadExecutor, MainThread mainThread,
                                            MoviesFragment moviesFragment,
                                           MovieRepository movieRepository) {
        return new MoviesPresenterImpl(threadExecutor, mainThread,
                moviesFragment, movieRepository);
    }

    @Provides
    @MoviesScope
    Executor providesThreadExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    @MoviesScope
    MainThread providesMainThread() {
        return new MainThreadImpl();
    }

}
