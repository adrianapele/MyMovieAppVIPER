package com.example.mymovieapp.view.fragments.details;

import androidx.fragment.app.FragmentActivity;

import com.example.mymovieapp.data.model.Movie;

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;
    private DetailsContract.Interactor interactor;
    private DetailsContract.Router router;

    public DetailsPresenter(DetailsContract.View view, DetailsContract.Interactor interactor, DetailsContract.Router router) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
    }

    @Override
    public void loadMovieById(int movieId) {
        Movie loadedMovie = interactor.loadMovieById(movieId);
        view.displayMovie(loadedMovie);
    }

    @Override
    public void onDeleteMoviePressed(Movie movie) {
        interactor.deleteMovie(movie);
        view.showMessage("Movie deleted!");
        router.navigateBackToFavoritesFragment(view);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
        router = null;
    }
}
