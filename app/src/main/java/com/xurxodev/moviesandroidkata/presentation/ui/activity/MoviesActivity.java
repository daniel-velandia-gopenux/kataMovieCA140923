package com.xurxodev.moviesandroidkata.presentation.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xurxodev.moviesandroidkata.databinding.ActivityMoviesBinding;
import com.xurxodev.moviesandroidkata.presentation.navigation.Navigate;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MovieDetailFragment;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MoviesFragment;

public class MoviesActivity extends AppCompatActivity {

    private ActivityMoviesBinding binding;
    private Navigate navigate;
    private MoviesFragment moviesFragment;
    private MovieDetailFragment movieDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeToolBar();

        if (savedInstanceState == null) {
            initializeNavigate();
            addMoviesFragment();
        }
    }
    private void initializeNavigate() {
        navigate = new Navigate(this);
    }

    private void initializeToolBar() {
        setSupportActionBar(binding.toolbar);
    }


    private void addMoviesFragment() {
        moviesFragment = new MoviesFragment();

        navigate.addFragment(binding.moviesListContainer.getId(), moviesFragment);
    }

    public void navigateToDetailFragment(int position) {
        movieDetailFragment = MovieDetailFragment.newInstance(position);

        navigate.navigateToFragment(binding.moviesListContainer.getId(), moviesFragment,
                movieDetailFragment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigate.navigateToBack(movieDetailFragment, moviesFragment);
    }
}
