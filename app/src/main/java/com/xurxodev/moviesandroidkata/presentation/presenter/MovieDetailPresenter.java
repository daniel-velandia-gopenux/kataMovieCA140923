package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.model.Movie;

import javax.inject.Inject;

public class MovieDetailPresenter {

    private final View movieDetailFragment;

    @Inject
    public MovieDetailPresenter(View movieDetailFragment) {
        this.movieDetailFragment = movieDetailFragment;
    }

    public void showMovie(Movie movie) {
        movieDetailFragment.movieLoaded(movie);
    }

    public interface View {

        void movieLoaded(Movie movie);
    }
}
