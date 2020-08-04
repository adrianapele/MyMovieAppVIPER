package com.example.mymovieapp.view.fragments.favorites;

import android.view.View;

import com.example.mymovieapp.data.model.Movie;

import java.util.List;

public class FavoritesPresenter implements FavoritesContract.Presenter {

    private FavoritesContract.View view;
    private FavoritesContract.Interactor interactor;
    private FavoritesContract.Router router;

    public FavoritesPresenter(FavoritesContract.View view, FavoritesContract.Interactor interactor, FavoritesContract.Router router) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
    }

    @Override
    public void onLoadSavedMovies() {
        view.showLoading();

        FavoritesFragment favoritesFragment = (FavoritesFragment) view;

        interactor
                .loadSavedMovies()
                .observe(favoritesFragment.getViewLifecycleOwner(), movies -> {
                    view.displayMovieList(movies);
                    view.hideLoading();
                });
    }

    @Override
    public void onDeleteAllMoviesPressed() {
        final List<Movie> movies = interactor.loadSavedMovies().getValue();

        if (movies == null || movies.isEmpty())
            view.showMessage("You don't have saved movies to delete");
        else
        {
            interactor.deleteAllMovies();
            view.showMessage("All saved movies were deleted");
        }
    }

    @Override
    public void onShowDetailsPressed(View view, int movieId) {
        router.navigateToDetailsFragment(view, movieId);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }
}
