package com.xurxodev.moviesandroidkata.domain.useCase;

import com.xurxodev.moviesandroidkata.domain.executor.Executor;
import com.xurxodev.moviesandroidkata.domain.executor.MainThread;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.entity.MovieEntity;
import com.xurxodev.moviesandroidkata.utils.MovieMapper;

import javax.inject.Inject;

public class GetMovieDetailUseCase {

    private Callback movieDetailPresenter;
    private MovieRepository movieRepository;
    private MainThread mainThread;
    private Executor backgroundExecutor;

    @Inject
    public GetMovieDetailUseCase(Callback movieDetailPresenter, MovieRepository movieRepository, MainThread mainThread,
                                 Executor backgroundExecutor) {
        this.movieDetailPresenter = movieDetailPresenter;
        this.movieRepository = movieRepository;
        this.mainThread = mainThread;
        this.backgroundExecutor = backgroundExecutor;
    }

    public void execute(int position) {
        backgroundExecutor.execute(new Runnable() {
            @Override
            public void run() {
                loading();

                MovieEntity movieEntity = movieRepository.getMovie(position);

                if(movieEntity == null) {
                    notifyError("error while read movie");
                    return;
                }

                Movie movie = MovieMapper.mapper(movieEntity);

                postMovie(movie);
            }
        });

    }

    private void loading() {
        mainThread.execute(new Runnable() {
            @Override
            public void run() {
                movieDetailPresenter.onMovieLoading();
            }
        });
    }

    private void notifyError(String error) {
        mainThread.execute(new Runnable() {
            @Override
            public void run() {
                movieDetailPresenter.onRetrievalFailed(error);
            }
        });
    }

    private void postMovie(Movie movie) {
        mainThread.execute(new Runnable() {
            @Override
            public void run() {
                movieDetailPresenter.onMovieRetrieved(movie);
            }
        });
    }

    public interface Callback {

        void onMovieLoading();
        void onMovieRetrieved(Movie movie);
        void onRetrievalFailed(String error);
    }
}
