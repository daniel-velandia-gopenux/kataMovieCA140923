package com.xurxodev.moviesandroidkata.domain.boundary;

import com.xurxodev.moviesandroidkata.domain.entity.MovieEntity;

import java.util.List;

public interface MovieRepository {

    List<MovieEntity> getMovies();
}
