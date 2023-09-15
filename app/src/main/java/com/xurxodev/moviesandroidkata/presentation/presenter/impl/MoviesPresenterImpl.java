package com.xurxodev.moviesandroidkata.presentation.presenter.impl;


import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.interactor.MoviesInteractor;
import com.xurxodev.moviesandroidkata.domain.interactor.impl.MoviesInteractorImpl;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.presenter.base.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenterImpl extends AbstractPresenter implements MoviesPresenter,
        MoviesInteractor.callback {

    private callback moviesFragment;
    private MovieRepository movieRepository;
    private MoviesInteractor moviesInteractor;

    @Inject
    public MoviesPresenterImpl(Executor threadExecutor, MainThread mainThread,
                               callback moviesFragment, MovieRepository movieRepository) {
        super(threadExecutor, mainThread);
        this.moviesFragment = moviesFragment;
        this.movieRepository = movieRepository;
        this.moviesInteractor = new MoviesInteractorImpl(threadExecutor, mainThread,
                this, movieRepository);
    }

    @Override
    public void onCreateView() {
        moviesFragment.initializeRefreshButton();
        moviesFragment.initializeAdapter();
        moviesFragment.initializeRecyclerView();

        moviesFragment.loadingMovies();

        moviesInteractor.execute();
    }

    @Override
    public void onMoviesRetrieved(List<Movie> movies) {
        moviesFragment.loadedMovies(movies);
    }

    @Override
    public void onRetrievalFailed(String error) {
        moviesFragment.showError(error);
    }

    @Override
    public void onRefreshMovies() {
        moviesFragment.loadingMovies();
        moviesInteractor.execute();
    }

}
