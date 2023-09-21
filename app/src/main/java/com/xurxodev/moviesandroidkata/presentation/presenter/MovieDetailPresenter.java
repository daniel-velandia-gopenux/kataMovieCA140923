package com.xurxodev.moviesandroidkata.presentation.presenter;

import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.useCase.GetMovieDetailUseCase;

import javax.inject.Inject;

public class MovieDetailPresenter {

    private GetMovieDetailUseCase getMovieDetailUseCase;
    private View movieFragment;

    @Inject
    public MovieDetailPresenter(GetMovieDetailUseCase getMovieDetailUseCase, View movieFragment) {
        this.getMovieDetailUseCase = getMovieDetailUseCase;
        this.movieFragment = movieFragment;
    }

    public void getMovie(int position) {
        getMovieDetailUseCase.execute(position, new GetMovieDetailUseCase.Callback() {
            @Override
            public void onMovieLoaded(Movie movie) {
                movieFragment.movieLoaded(movie);
            }
        });
    }


    public interface View {

        void movieLoaded(Movie movie);
    }
}
