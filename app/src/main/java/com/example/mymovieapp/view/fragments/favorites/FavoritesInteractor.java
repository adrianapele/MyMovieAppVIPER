package com.example.mymovieapp.view.fragments.favorites;

import androidx.lifecycle.LiveData;

import com.example.mymovieapp.data.model.Movie;
import com.example.mymovieapp.data.repository.MovieRepository;

import java.util.List;

public class FavoritesInteractor implements FavoritesContract.Interactor {

    private MovieRepository movieRepository;

    public FavoritesInteractor(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public LiveData<List<Movie>> loadSavedMovies() {
        return movieRepository.getAllSavedMovies();
    }

    @Override
    public void deleteAllMovies() {
        movieRepository.deleteAllSavedMovies();
    }
}
