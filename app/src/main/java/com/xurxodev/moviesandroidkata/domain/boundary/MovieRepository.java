package com.xurxodev.moviesandroidkata.domain.boundary;

import com.xurxodev.moviesandroidkata.domain.model.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> getMovies();
}
