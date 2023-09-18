package com.xurxodev.moviesandroidkata.domain.useCase;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.entity.MovieEntity;
import com.xurxodev.moviesandroidkata.utils.MovieMapper;

import java.util.List;

import javax.inject.Inject;

public class GetMoviesUseCase {

    private Callback moviesPresenter;
    private MovieRepository movieRepository;
    private MainThread mainHandler;
    private Executor backgroundExecutor;

    @Inject
    public GetMoviesUseCase(Callback moviesPresenter, MovieRepository movieRepository, MainThread mainHandler,
                            Executor backgroundExecutor) {
        this.moviesPresenter = moviesPresenter;
        this.movieRepository = movieRepository;
        this.mainHandler = mainHandler;
        this.backgroundExecutor = backgroundExecutor;
    }

    public void execute() {
        backgroundExecutor.execute(new Runnable() {
            @Override
            public void run() {
                loading();

                List<MovieEntity> movieEntities = movieRepository.getMovies();

                if(movieEntities == null || movieEntities.size() == 0) {
                    notifyError("error while read movies");
                    return;
                }

                List<Movie> movies = MovieMapper.mapper(movieEntities);

                postMovies(movies);
            }
        });

    }

    private void loading() {
        mainHandler.execute(new Runnable() {
            @Override
            public void run() {
                moviesPresenter.onMoviesLoading();
            }
        });
    }

    private void notifyError(String error) {
        mainHandler.execute(new Runnable() {
            @Override
            public void run() {
                moviesPresenter.onRetrievalFailed(error);
            }
        });
    }

    private void postMovies(List<Movie> movies) {
        mainHandler.execute(new Runnable() {
            @Override
            public void run() {
                moviesPresenter.onMoviesRetrieved(movies);
            }
        });
    }

    public interface Callback {

        void onMoviesLoading();
        void onMoviesRetrieved(List<Movie> movies);
        void onRetrievalFailed(String message);
    }
}
