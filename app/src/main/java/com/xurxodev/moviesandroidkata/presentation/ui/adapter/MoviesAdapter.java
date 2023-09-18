package com.xurxodev.moviesandroidkata.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.xurxodev.moviesandroidkata.databinding.ItemMoviesBinding;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.presentation.ui.event.OnClickItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MoviesAdapter
        extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnClickItem onClickItem;
    @Inject
    protected Picasso picasso;

    public MoviesAdapter(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void clearMovies() {
        movies = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMoviesBinding binding = ItemMoviesBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ItemMoviesBinding binding;

        public ViewHolder(ItemMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movieItem) {

            picasso.get().load(movieItem.getImage()).into(binding.itemMoviePoster);

            binding.itemMovieTitle.setText(movieItem.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItem.onClick(getAdapterPosition());
                }
            });
        }
    }
}
