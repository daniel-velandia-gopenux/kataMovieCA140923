package com.xurxodev.moviesandroidkata.domain.useCase;

import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.entity.MovieEntity;
import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.utils.MovieMapper;

import javax.inject.Inject;

public class GetMovieDetailUseCase implements Runnable {

    private MainThread mainThread;
    private Executor backgroundExecutor;
    private MovieRepository movieRepository;

    private Callback callback;
    private int position;

    @Inject
    public GetMovieDetailUseCase(MainThread mainThread, Executor backgroundExecutor,
                                 MovieRepository movieRepository) {
        this.mainThread = mainThread;
        this.backgroundExecutor = backgroundExecutor;
        this.movieRepository = movieRepository;
    }

    @Inject
    public void execute(int position, Callback callback) {
        this.position = position;
        this.callback = callback;
        backgroundExecutor.execute(this);
    }

    @Override
    public void run() {
        MovieEntity movieEntity = movieRepository.getMovie(position);

        Movie movie = MovieMapper.mapper(movieEntity);

        movieLoaded(movie);
    }

    private void movieLoaded(Movie movie) {
        mainThread.execute(new Runnable() {
            @Override
            public void run() {
                callback.onMovieLoaded(movie);
            }
        });
    }

    public interface Callback {

        void onMovieLoaded(Movie movie);
    }
}
