package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.useCase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.presentation.navigation.Navigator;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenter {

    private final GetMoviesUseCase getMoviesUseCase;
    private final View moviesFragment;
    private final Navigator navigator;

    @Inject
    public MoviesPresenter(GetMoviesUseCase getMoviesUseCase, View moviesFragment, Navigator navigator) {
        this.getMoviesUseCase = getMoviesUseCase;
        this.moviesFragment = moviesFragment;
        this.navigator = navigator;
    }

    public void initializeElements() {
        moviesFragment.initializeRefreshButton();
        moviesFragment.initializeAdapter();
        moviesFragment.initializeRecyclerView();
    }

    public void getMovies() {
        moviesFragment.loadingMovies();
        getMoviesUseCase.execute(new GetMoviesUseCase.Callback() {

            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                moviesFragment.loadedMovies(movies);
            }
        });
    }

    public void navigateToDetail(Movie movie) {
        navigator.navigateToDetail(movie);
    }

    public interface View {

        void initializeRefreshButton();
        void initializeAdapter();
        void initializeRecyclerView();

        void loadingMovies();

        void loadedMovies(List<Movie> movies);
    }
}
