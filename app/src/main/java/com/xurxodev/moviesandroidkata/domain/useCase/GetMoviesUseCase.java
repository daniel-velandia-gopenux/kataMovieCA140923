package com.xurxodev.moviesandroidkata.domain.useCase;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.entity.MovieEntity;
import com.xurxodev.moviesandroidkata.utils.MovieMapper;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

public class GetMoviesUseCase implements Runnable {

    private MainThread mainHandler;
    private Executor backgroundExecutor;

    private Lazy<Callback> callback;
    private MovieRepository movieRepository;

    @Inject
    public GetMoviesUseCase(MainThread mainHandler, Executor backgroundExecutor,
                            Lazy<Callback> callback, MovieRepository movieRepository) {
        this.mainHandler = mainHandler;
        this.backgroundExecutor = backgroundExecutor;
        this.callback = callback;
        this.movieRepository = movieRepository;
    }

    public void execute() {
        backgroundExecutor.execute(this);
    }

    @Override
    public void run() {

        List<MovieEntity> movieEntities = movieRepository.getMovies();

        List<Movie> movies = MovieMapper.mapper(movieEntities);

        moviesLoaded(movies);

    }

    private void moviesLoaded(List<Movie> movies) {
        mainHandler.execute(new Runnable() {
            @Override
            public void run() {
                callback.get().onMoviesLoaded(movies);
            }
        });
    }

    public interface Callback {

        void onMoviesLoaded(List<Movie> movies);
    }
}
