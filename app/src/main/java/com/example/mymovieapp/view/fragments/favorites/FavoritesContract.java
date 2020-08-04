package com.example.mymovieapp.view.fragments.favorites;

import android.view.View;

import androidx.lifecycle.LiveData;

import com.example.mymovieapp.data.model.Movie;

import java.util.List;

interface FavoritesContract {

    interface View {
        void showLoading();
        void hideLoading();

        void displayMovieList(List<Movie> movies);
        void showMessage(String message);
    }

    interface Presenter {
        void onLoadSavedMovies();
        void onDeleteAllMoviesPressed();
        void onShowDetailsPressed(android.view.View view, int movieId);
        void onDestroy();
    }

    interface Interactor {
        LiveData<List<Movie>> loadSavedMovies();
        void deleteAllMovies();
    }

    interface Router {
        void navigateToDetailsFragment(android.view.View view, int movieId);
    }
}
