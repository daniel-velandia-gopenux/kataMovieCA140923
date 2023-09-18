package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.useCase.GetMovieDetailUseCase;
import com.xurxodev.moviesandroidkata.domain.model.Movie;

public class MovieDetailPresenter implements GetMovieDetailUseCase.Callback{

    private Callback movieDetailFragment;

    public MovieDetailPresenter(Callback movieDetailFragment) {
        this.movieDetailFragment = movieDetailFragment;
    }

    @Override
    public void onMovieLoading() {
        movieDetailFragment.loadingMovie();
    }

    @Override
    public void onMovieRetrieved(Movie movie) {
        movieDetailFragment.loadedMovie(movie);
    }

    @Override
    public void onRetrievalFailed(String error) {
        movieDetailFragment.showError(error);
    }

    public interface Callback {

        void loadingMovie();
        void loadedMovie(Movie movie);
        void showError(String error);

    }
}
