package com.xurxodev.moviesandroidkata.presentation.controller;

import com.xurxodev.moviesandroidkata.domain.useCase.GetMoviesUseCase;

import javax.inject.Inject;

public class MoviesController {

    private GetMoviesUseCase getMoviesUseCase;

    @Inject
    public MoviesController(GetMoviesUseCase getMoviesUseCase) {
        this.getMoviesUseCase = getMoviesUseCase;
    }

    public void executeUseCase() {
        getMoviesUseCase.execute();
    }
}
