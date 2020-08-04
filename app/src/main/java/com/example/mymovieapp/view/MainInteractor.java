package com.example.mymovieapp.view;

import androidx.lifecycle.LiveData;

import com.example.mymovieapp.data.model.Movie;
import com.example.mymovieapp.data.repository.MovieRepository;

import java.util.List;

public class MainInteractor implements MainContract.Interactor{

    private MovieRepository movieRepository;

    public MainInteractor(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> loadSavedMovies() {
        return movieRepository.getAllSavedMovies().getValue();
    }
}
