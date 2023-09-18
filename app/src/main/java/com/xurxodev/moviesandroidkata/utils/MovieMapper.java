package com.xurxodev.moviesandroidkata.utils;

import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.entity.MovieEntity;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public static Movie mapper(MovieEntity movieEntity) {
        Movie movie = new Movie();

        movie.setImage(movieEntity.getImage());
        movie.setTitle(movieEntity.getTitle());
        movie.setDescription(movieEntity.getDescription());

        return movie;
    }

    public static List<Movie> mapper(List<MovieEntity> movieEntities) {
        List<Movie> movies = new ArrayList<>();

        for(int position = 0; position < movieEntities.size(); position++) {
            MovieEntity movieEntity = movieEntities.get(position);

            Movie movie = new Movie();

            movie.setImage(movieEntity.getImage());
            movie.setTitle(movieEntity.getTitle());
            movie.setDescription(movieEntity.getDescription());

            movies.add(movie);

        }

        return movies;
    }
}
