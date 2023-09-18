package com.xurxodev.moviesandroidkata.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import com.xurxodev.moviesandroidkata.MoviesApplication;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.databinding.FragmentMovieDetailBinding;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.presentation.controller.MovieDetailController;
import com.xurxodev.moviesandroidkata.presentation.presenter.MovieDetailPresenter;

import javax.inject.Inject;

public class MovieDetailFragment extends Fragment implements MovieDetailPresenter.Callback {

    private static final String ARG_POSITION = "position";

    private int position;
    private FragmentMovieDetailBinding binding;

    @Inject
    MovieDetailController movieDetailController;
    @Inject
    Picasso picasso;

    public MovieDetailFragment() {

    }

    public static MovieDetailFragment newInstance(int position) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }

        ((MoviesApplication) getActivity().getApplication()).getMoviesComponent()
                .getMovieDetailSubComponent().create(this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false);

        movieDetailController.executeUseCase(position);

        return binding.getRoot();
    }

    @Override
    public void loadingMovie() {
        binding.loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadedMovie(Movie movie) {
        binding.loading.setVisibility(View.GONE);

        picasso.get().load(movie.getImage()).into(binding.itemMoviePoster);
        binding.itemMovieTitle.setText(movie.getTitle());
        binding.itemMovieOverview.setText(R.string.overview);
        binding.itemMovieDescription.setText(movie.getDescription());
    }

    @Override
    public void showError(String error) {
        binding.loading.setText(error);
    }
}