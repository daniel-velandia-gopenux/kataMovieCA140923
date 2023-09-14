package com.xurxodev.moviesandroidkata.data;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.domain.model.Movie;
import com.xurxodev.moviesandroidkata.domain.boundary.MovieRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class MovieRepositoryImpl implements MovieRepository {

    private final Context applicationContext;
    private final Gson gson;

    @Inject
    public MovieRepositoryImpl(Application applicationContext, Gson gson){
       this.applicationContext = applicationContext;
       this.gson = gson;
    }

    @Override
    public List<Movie> getMovies() {

        Movie[] movies = transformGson();

        simulateDelay();

        return Arrays.asList(movies);
    }

    private Movie[] transformGson() {
        try {
            String jsonString = getJsonString();
            return gson.fromJson(jsonString, Movie[].class);
        } catch (IOException e){
            Timber.tag("error while read file").e(e);
        }
        return new Movie[0];
    }

    private String getJsonString() throws IOException {
        InputStream inputStream = applicationContext.getResources().openRawResource(R.raw.movies);
        byte[] b = new byte[inputStream.available()];
        inputStream.read(b);

        return new String(b);
    }

    private void simulateDelay(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
