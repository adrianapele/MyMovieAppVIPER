package com.example.mymovieapp.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.mymovieapp.data.model.Movie;
import com.example.mymovieapp.data.repository.MovieRepository;

import java.util.List;

public class MainInteractor implements MainContract.Interactor{

    private MovieRepository movieRepository;

    public MainInteractor(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public LiveData<List<Movie>> loadSavedMovies() {
        return movieRepository.getAllSavedMovies();
    }
}
