package com.xurxodev.moviesandroidkata.presentation.navigation;

import static com.xurxodev.moviesandroidkata.presentation.ui.activity.MovieDetailActivity.EXTRA_MOVIE;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.presentation.ui.activity.MovieDetailActivity;

import javax.inject.Inject;

public class Navigator {
    private final AppCompatActivity activity;

    @Inject
    public Navigator(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void navigateToDetail(Movie movie) {
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        activity.startActivity(intent);
    }
}
