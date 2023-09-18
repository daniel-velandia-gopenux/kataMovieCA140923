package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.useCase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.domain.model.Movie;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenter implements GetMoviesUseCase.Callback {

    private Callback moviesFragment;

    @Inject
    public MoviesPresenter(Callback moviesFragment) {
        this.moviesFragment = moviesFragment;
    }

    @Override
    public void onMoviesLoading() {
        moviesFragment.loadingMovies();
    }

    @Override
    public void onMoviesRetrieved(List<Movie> movies) {
        moviesFragment.loadedMovies(movies);
    }

    @Override
    public void onRetrievalFailed(String error) {
        moviesFragment.showError(error);
    }

    public interface Callback {

        void loadingMovies();
        void loadedMovies(List<Movie> movies);
        void showError(String error);
    }

}
