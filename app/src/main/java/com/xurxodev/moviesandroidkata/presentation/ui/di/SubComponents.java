package com.xurxodev.moviesandroidkata.presentation.ui.di;

import com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.movieDetailSubComponent.MovieDetailSubComponent;
import com.xurxodev.moviesandroidkata.presentation.ui.di.subComponent.moviesSubComponent.MoviesSubComponent;

import dagger.Module;

@Module(subcomponents = {MoviesSubComponent.class, MovieDetailSubComponent.class})
public class SubComponents {
}
