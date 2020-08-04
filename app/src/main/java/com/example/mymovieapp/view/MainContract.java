package com.example.mymovieapp.view;

import androidx.lifecycle.LiveData;

import com.example.mymovieapp.data.model.Movie;

import java.util.List;

public interface MainContract {

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void shareAllSavedMovies();
    }

    interface Interactor {
        List<Movie> loadSavedMovies();
    }

    interface Router {
        void openIntent(View view, String savedMoviesToText);
    }
}
