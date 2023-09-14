package com.xurxodev.moviesandroidkata.domain.interactor.impl;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.interactor.base.AbstracInteractor;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;

import java.util.List;

public class MoviesInteractor extends AbstracInteractor {

    private callback callback;
    private MovieRepository movieRepository;

    public MoviesInteractor(Executor threadExecutor, MainThread mainThread, callback callback, MovieRepository movieRepository) {
        super(threadExecutor, mainThread);
        this.callback = callback;
        this.movieRepository = movieRepository;
    }

    private void notifyError() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onRetrievalFailed("Error while read movies");
            }
        });
    }

    private void postMovies(List<Movie> movies) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onMoviesRetrieved(movies);
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

    public interface callback {

        void onMoviesRetrieved(List<Movie> movies);
        void onRetrievalFailed(String error);
    }

}
