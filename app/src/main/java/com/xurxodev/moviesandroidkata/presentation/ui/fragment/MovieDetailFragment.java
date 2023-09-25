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
import com.xurxodev.moviesandroidkata.presentation.presenter.MovieDetailPresenter;

import javax.inject.Inject;

public class MovieDetailFragment extends Fragment implements MovieDetailPresenter.View {

    private static final String ARG_MOVIE = "movie";
    private Movie movie;
    private FragmentMovieDetailBinding binding;

    @Inject
    MovieDetailPresenter movieDetailPresenter;
    @Inject
    Picasso picasso;

    public MovieDetailFragment() {

    }

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = (Movie) getArguments().getSerializable(ARG_MOVIE);
        }
        initializeComponent();
    }

    private void initializeComponent() {
        MoviesApplication moviesApplication = (MoviesApplication) getActivity().getApplication();
        moviesApplication.getMoviesComponent()
                .getMovieDetailSubComponent()
                .create(this)
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false);

        movieDetailPresenter.showMovie(movie);

        return binding.getRoot();
    }

    @Override
    public void movieLoaded(Movie movie) {
        binding.progress.setVisibility(View.GONE);
        picasso.get().load(movie.getImage()).into(binding.itemMoviePoster);
        binding.itemMovieOverview.setText(R.string.overview);
        binding.itemMovieTitle.setText(movie.getTitle());
        binding.itemMovieDescription.setText(movie.getDescription());
    }
}