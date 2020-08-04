package com.example.mymovieapp.view.fragments.details;

import androidx.lifecycle.LiveData;

import com.example.mymovieapp.data.model.Movie;

import java.util.List;

public interface DetailsContract {

    interface View {
        void displayMovie(Movie movie);
        void showMessage(String message);
    }

    interface Presenter {
        void loadMovieById(int movieId);
        void onDeleteMoviePressed(Movie movie);
        void onDestroy();
    }

    interface Interactor {
        Movie loadMovieById(int movieId);
        void deleteMovie(Movie movie);
    }

    interface Router {
        void navigateBackToFavoritesFragment(View view);
    }
}
