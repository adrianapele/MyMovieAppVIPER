package com.example.mymovieapp.view;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.mymovieapp.data.model.Movie;

import java.util.List;

public interface MainContract {

    interface View {
        void showMessage(String message);

        LifecycleOwner getLifeCycleOwner();
    }

    interface Presenter {
        void shareAllSavedMovies();
    }

    interface Interactor {
        LiveData<List<Movie>> loadSavedMovies();
    }

    interface Router {
        void openIntent(View view, String savedMoviesToText);
    }
}
