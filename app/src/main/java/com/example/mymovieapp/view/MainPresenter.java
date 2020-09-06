package com.example.mymovieapp.view;

import com.example.mymovieapp.data.model.Movie;

import java.util.stream.Collectors;

public class MainPresenter implements MainContract.Presenter{

    private MainContract.Interactor interactor;
    private MainContract.Router router;
    private MainContract.View view;

    public MainPresenter(MainContract.Interactor interactor, MainContract.Router router, MainContract.View view) {
        this.interactor = interactor;
        this.router = router;
        this.view = view;
    }

    @Override
    public void shareAllSavedMovies() {

        interactor.loadSavedMovies().observe(view.getLifeCycleOwner(), movies -> {
            if (movies == null || movies.size() == 0)
                view.showMessage("You don't have saved movies to share");
            else {
                final String savedMoviesToText = movies
                        .stream()
                        .map(Movie::toText)
                        .collect(Collectors.joining(System.getProperty("line.separator")));

                router.openIntent(view, savedMoviesToText);
            }
        });
    }
}
