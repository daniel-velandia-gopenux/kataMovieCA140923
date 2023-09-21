package com.xurxodev.moviesandroidkata.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xurxodev.moviesandroidkata.MoviesApplication;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.databinding.FragmentMoviesBinding;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.presentation.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presentation.ui.activity.MoviesActivity;
import com.xurxodev.moviesandroidkata.presentation.ui.adapter.MoviesAdapter;
import com.xurxodev.moviesandroidkata.presentation.ui.event.OnClickItemListener;

import java.util.List;

import javax.inject.Inject;

public class MoviesFragment extends Fragment implements MoviesPresenter.View {

    private MoviesAdapter adapter;
    private FragmentMoviesBinding binding;
    @Inject
    MoviesPresenter moviesPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeComponent();

    }

    private void initializeComponent() {
        MoviesApplication moviesApplication = (MoviesApplication) getActivity().getApplication();
        moviesApplication.getMoviesComponent()
                .getMoviesSubComponent()
                .create(this)
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentMoviesBinding.inflate(inflater, container, false);

        initializeRefreshButton();
        initializeAdapter();
        initializeRecyclerView();

        moviesPresenter.getMovies();

        return binding.getRoot();
    }

    public void initializeRefreshButton() {
        binding.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesPresenter.getMovies();
            }
        });
    }

    public void initializeAdapter() {
        adapter = new MoviesAdapter(new OnClickItemListener() {
            @Override
            public void onClick(int position) {
                MoviesActivity moviesActivity = ((MoviesActivity) getActivity());
                moviesActivity.navigateToDetailFragment(position);
            }
        });
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
}
