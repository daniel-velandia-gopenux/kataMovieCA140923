package com.xurxodev.moviesandroidkata.presentation.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xurxodev.moviesandroidkata.databinding.ActivityMoviesBinding;
import com.xurxodev.moviesandroidkata.presentation.navigation.Navigation;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MovieDetailFragment;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MoviesFragment;

public class MoviesActivity extends AppCompatActivity {

    private ActivityMoviesBinding binding;
    private Navigation navigation;
    private MoviesFragment moviesFragment;
    private MovieDetailFragment movieDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeToolBar();

        if (savedInstanceState == null) {
            initializeNavigation();
            addMoviesFragment();
        }
    }

    private void initializeToolBar() {
        setSupportActionBar(binding.toolbar);
    }

    private void initializeNavigation() {
        navigation = new Navigation(this, binding.moviesListContainer.getId());
    }

    private void addMoviesFragment() {
        moviesFragment = new MoviesFragment();

        navigation.addFragment(moviesFragment);
    }

    public void goToDetailFragment(int position) {
        movieDetailFragment = MovieDetailFragment.newInstance(position);

        navigation.navigateToFragment(moviesFragment, movieDetailFragment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigation.navigateToBack(moviesFragment, movieDetailFragment);
    }
}
