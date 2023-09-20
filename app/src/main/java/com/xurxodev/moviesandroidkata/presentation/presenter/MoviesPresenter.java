package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.useCase.GetMoviesUseCase;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenter implements GetMoviesUseCase.Callback {

    private GetMoviesUseCase getMoviesUseCase;
    private View moviesFragment;

    @Inject
    public MoviesPresenter(GetMoviesUseCase getMoviesUseCase, View moviesFragment) {
        this.getMoviesUseCase = getMoviesUseCase;
        this.moviesFragment = moviesFragment;
    }

    public void getMovies() {
        moviesFragment.loadingMovies();
        getMoviesUseCase.execute();
    }

    @Override
    public void onMoviesLoaded(List<Movie> movies) {
        moviesFragment.loadedMovies(movies);
    }

    public interface View {

        void loadingMovies();

        void loadedMovies(List<Movie> movies);
    }
}
