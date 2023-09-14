package com.xurxodev.moviesandroidkata.presentation.presenter.impl;


import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.interactor.impl.MoviesInteractor;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.presentation.presenter.base.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenter extends AbstractPresenter implements MoviesInteractor.callback {

    private callback view;
    private MovieRepository movieRepository;
    @Inject
    private MoviesInteractor interactor;

    @Inject
    public MoviesPresenter(Executor threadExecutor, MainThread mainThread, callback view, MovieRepository movieRepository) {
        super(threadExecutor, mainThread);
        this.view = view;
        this.movieRepository = movieRepository;
    }

    @Override
    public void onCreateView() {
        view.loadingMovies();

        interactor.execute();
    }

    @Override
    public void onMoviesRetrieved(List<Movie> movies) {
        view.loadedMovies(movies);
    }

    @Override
    public void onRetrievalFailed(String error) {
        view.showError(error);
    }

    public void onRefreshMovies() {
        interactor.execute();
    }

    public interface callback {

        void loadingMovies();

        void loadedMovies(List<Movie> movies);

        void showError(String error);
    }

}
