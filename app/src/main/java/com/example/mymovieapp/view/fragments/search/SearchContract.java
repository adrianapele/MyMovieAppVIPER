package com.example.mymovieapp.view.fragments.search;

import androidx.lifecycle.LiveData;

import com.example.mymovieapp.data.model.Movie;

import java.util.List;

public interface SearchContract {

    interface View {
        void showLoading();
        void hideLoading();

        void displayMovieList(List<Movie> movies);
        void showMessage(String message);
    }

    interface Presenter {
        void onSaveButtonClicked(List<Movie> moviesToSave);
        void onSearchMovieIconClicked(String title);
        void onLoadRandomMovies();
        void onDestroy();
    }

    interface Interactor {
        void saveMovies(List<Movie> moviesToSave);
        LiveData<List<Movie>> searchRandomMovies();
        LiveData<List<Movie>> searchMovies(String title);
    }
}
