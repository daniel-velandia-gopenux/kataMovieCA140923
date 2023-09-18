package com.xurxodev.moviesandroidkata.presentation.controller;

import com.xurxodev.moviesandroidkata.domain.useCase.GetMovieDetailUseCase;

import javax.inject.Inject;

public class MovieDetailController {

    private GetMovieDetailUseCase getMovieDetailUseCase;

    @Inject
    public MovieDetailController(GetMovieDetailUseCase getMovieDetailUseCase) {
        this.getMovieDetailUseCase = getMovieDetailUseCase;
    }

    public void executeUseCase(int position) {
        getMovieDetailUseCase.execute(position);
    }
}
