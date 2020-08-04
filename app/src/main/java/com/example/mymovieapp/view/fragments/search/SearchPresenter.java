package com.example.mymovieapp.view.fragments.search;

import androidx.lifecycle.LifecycleOwner;

import com.example.mymovieapp.data.model.Movie;

import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;
    private SearchContract.Interactor interactor;

    public SearchPresenter(SearchContract.View view, SearchContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onSaveButtonClicked(List<Movie> moviesToSave) {
        final List<Movie> checkedMovies = moviesToSave
                .stream()
                .filter(Movie::isWatched)
                .collect(Collectors.toList());

        if (checkedMovies.isEmpty())
            view.showMessage("You don't have any checked movies to save");
        else
        {
            interactor.saveMovies(checkedMovies);
            view.showMessage("Checked movies were saved!");
        }
    }

    @Override
    public void onSearchMovieIconClicked(String title) {
        view.showLoading();

        final SearchFragment searchFragment = (SearchFragment) view;
        final LifecycleOwner viewLifecycleOwner = searchFragment.getViewLifecycleOwner();

        interactor.searchMovies(title).observe(viewLifecycleOwner, movies -> {
            view.displayMovieList(movies);
            view.hideLoading();
        });
    }

    @Override
    public void onLoadRandomMovies() {
        view.showLoading();

        final SearchFragment searchFragment = (SearchFragment) view;
        final LifecycleOwner viewLifecycleOwner = searchFragment.getViewLifecycleOwner();

        interactor.searchRandomMovies().observe(viewLifecycleOwner, movies -> {
            view.displayMovieList(movies);
            view.hideLoading();
        });
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }
}
