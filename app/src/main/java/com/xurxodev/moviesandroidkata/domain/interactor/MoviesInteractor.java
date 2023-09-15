package com.xurxodev.moviesandroidkata.domain.interactor;

import com.xurxodev.moviesandroidkata.domain.interactor.base.Interactor;
import com.xurxodev.moviesandroidkata.domain.model.Movie;

import java.util.List;

public interface MoviesInteractor extends Interactor {

    interface callback {

        void onMoviesRetrieved(List<Movie> movies);
        void onRetrievalFailed(String error);
    }
}
