package com.xurxodev.moviesandroidkata.presentation.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xurxodev.moviesandroidkata.databinding.ActivityMovieDetailBinding;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MovieDetailFragment;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;
    public static final String EXTRA_MOVIE = "extraMovie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeToolBar();

        if (savedInstanceState == null) {
            addMoviesFragment();
        }
    }

    private void initializeToolBar() {
        setSupportActionBar(binding.toolbar);
    }

    private void addMoviesFragment() {
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(movie);

        getSupportFragmentManager().beginTransaction()
                .add(binding.movieDetailContainer.getId(), movieDetailFragment)
                .commit();
    }
}
