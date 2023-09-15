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
import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.ui.adapter.MoviesAdapter;

import java.util.List;

import javax.inject.Inject;

public class MoviesFragment extends Fragment implements MoviesPresenter.callback {

    private MoviesAdapter adapter;
    private FragmentMoviesBinding binding;
    @Inject
    MoviesPresenter moviesPresenter;

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

        moviesPresenter.onCreateView();

        return binding.getRoot();
    }

    @Override
    public void initializeRefreshButton() {
        binding.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesPresenter.onRefreshMovies();
            }
        });
    }

    @Override
    public void initializeAdapter() {
        adapter = new MoviesAdapter();
    }

    @Override
    public void initializeRecyclerView() {
        binding.recyclerviewMovies.setAdapter(adapter);
    }

    @Override
    public void loadedMovies(List<Movie> movies) {
        adapter.setMovies(movies);
        refreshTitleWithMoviesCount(movies);
    }

    private void refreshTitleWithMoviesCount(List<Movie> movies) {
        String countText = getString(R.string.movies_count_text);

        binding.moviesTitleTextView.setText(String.format(countText, movies.size()));
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
