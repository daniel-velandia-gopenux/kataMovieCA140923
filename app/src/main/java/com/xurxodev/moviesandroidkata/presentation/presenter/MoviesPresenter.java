package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.presentation.presenter.base.Presenter;

import java.util.List;

public interface MoviesPresenter extends Presenter {

    void onRefreshMovies();

    interface callback {

        void initializeRefreshButton();
        void initializeAdapter();
        void initializeRecyclerView();

        void loadingMovies();
        void loadedMovies(List<Movie> movies);
        void showError(String error);
    }
}
