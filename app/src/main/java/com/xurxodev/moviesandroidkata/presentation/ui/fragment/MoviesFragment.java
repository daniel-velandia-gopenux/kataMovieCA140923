package com.xurxodev.moviesandroidkata.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xurxodev.moviesandroidkata.MoviesApplication;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.databinding.FragmentMoviesBinding;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.presentation.controller.MoviesController;
import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.ui.activity.MoviesActivity;
import com.xurxodev.moviesandroidkata.presentation.ui.adapter.MoviesAdapter;
import com.xurxodev.moviesandroidkata.presentation.ui.event.OnClickItem;

import java.util.List;

import javax.inject.Inject;

public class MoviesFragment extends Fragment implements MoviesPresenter.Callback {

    private MoviesAdapter adapter;
    private FragmentMoviesBinding binding;
    @Inject
    MoviesController moviesController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MoviesApplication) getActivity().getApplication()).getMoviesComponent()
                .getMovieSubComponent().create(this).inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentMoviesBinding.inflate(inflater, container, false);

        initializeRefreshButton();
        initializeAdapter();
        initializeRecyclerView();

        moviesController.executeUseCase();

        return binding.getRoot();
    }

    public void initializeRefreshButton() {
        binding.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesController.executeUseCase();
            }
        });
    }

    public void initializeAdapter() {
        adapter = new MoviesAdapter(new OnClickItem() {
            @Override
            public void onClick(int position) {
                goToDetailFragment(position);
            }
        });
    }

    private void goToDetailFragment(int position) {
        MoviesActivity moviesActivity = ((MoviesActivity) getActivity());
        moviesActivity.goToDetailFragment(position);
    }

    public void initializeRecyclerView() {
        binding.recyclerviewMovies.setAdapter(adapter);
    }

    @Override
    public void loadedMovies(List<Movie> movies) {
        adapter.setMovies(movies);
        refreshTitleWithMoviesCount(movies.size());
    }

    private void refreshTitleWithMoviesCount(int size) {
        String countText = getString(R.string.movies_count_text);

        binding.moviesTitleTextView.setText(String.format(countText, size));
    }

    @Override
    public void loadingMovies() {
        adapter.clearMovies();
        binding.moviesTitleTextView.setText(R.string.loading_movies_text);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
