package com.example.mymovieapp.view.fragments.search;

import androidx.lifecycle.LiveData;

import com.example.mymovieapp.data.model.Movie;
import com.example.mymovieapp.data.repository.MovieRepository;

import java.util.List;

public class SearchInteractor implements SearchContract.Interactor {

    private final MovieRepository movieRepository;

    public SearchInteractor(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    @Override
    public void saveMovies(List<Movie> moviesToSave) {
        moviesToSave.forEach(movieRepository::insert);
    }

    @Override
    public LiveData<List<Movie>> searchRandomMovies() {
        return movieRepository.getAllMovies();
    }

    @Override
    public LiveData<List<Movie>> searchMovies(String title) {
        return movieRepository.searchMovies(title);
    }
}
