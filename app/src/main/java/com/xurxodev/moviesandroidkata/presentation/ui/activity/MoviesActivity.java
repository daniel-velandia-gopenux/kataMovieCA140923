package com.xurxodev.moviesandroidkata.presentation.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xurxodev.moviesandroidkata.databinding.ActivityMoviesBinding;
import com.xurxodev.moviesandroidkata.presentation.ui.fragment.MoviesFragment;

public class MoviesActivity extends AppCompatActivity {

    private ActivityMoviesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeToolBar();

        if (savedInstanceState == null) {
            initializeFragment();
        }
    }

    private void initializeToolBar() {
        setSupportActionBar(binding.toolbar);
    }


    private void initializeFragment() {
        MoviesFragment moviesFragment = new MoviesFragment();

        getSupportFragmentManager().beginTransaction()
                .add(binding.moviesListContainer.getId(), moviesFragment)
                .commit();
    }
}
