package com.example.mymovieapp.view.fragments.details;

import com.example.mymovieapp.data.model.Movie;
import com.example.mymovieapp.data.repository.MovieRepository;

public class DetailsInteractor implements DetailsContract.Interactor {

    private MovieRepository movieRepository;

    public DetailsInteractor(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie loadMovieById(int movieId) {
        return movieRepository.getMovieById(movieId);
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }
}
