package com.xurxodev.moviesandroidkata.domain.interactor.impl;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.interactor.MoviesInteractor;
import com.xurxodev.moviesandroidkata.domain.interactor.base.AbstracInteractor;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;

import java.util.List;

public class MoviesInteractorImpl extends AbstracInteractor implements MoviesInteractor {

    private callback moviesPresenter;
    private MovieRepository movieRepository;

    public MoviesInteractorImpl(Executor threadExecutor, MainThread mainThread, callback moviesPresenter, MovieRepository movieRepository) {
        super(threadExecutor, mainThread);
        this.moviesPresenter = moviesPresenter;
        this.movieRepository = movieRepository;
    }

    private void notifyError() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                moviesPresenter.onRetrievalFailed("Error while read movies");
            }
        });
    }

    private void postMovies(List<Movie> movies) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                moviesPresenter.onMoviesRetrieved(movies);
            }
        });
    }

    @Override
    public void run() {
        List<Movie> movies = movieRepository.getMovies();

        if(movies == null || movies.size() == 0) {
            notifyError();
            return;
        }

        postMovies(movies);
    }



}
