package com.example.mymovieapp.view.fragments.search;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovieapp.R;
import com.example.mymovieapp.data.model.Movie;
import com.example.mymovieapp.data.repository.MovieRepository;
import com.example.mymovieapp.view.adapters.SearchAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SearchFragment extends Fragment implements SearchContract.View {

    private SearchContract.Presenter presenter;

    private SearchAdapter searchAdapter;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private EditText searchEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MovieRepository movieRepository = new MovieRepository(getActivity().getApplication());
        final SearchInteractor interactor = new SearchInteractor(movieRepository);
        presenter = new SearchPresenter(this, interactor);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        final ImageView searchIcon = rootView.findViewById(R.id.searchImageViewId);
        searchIcon.setOnClickListener(searchViewIcon -> presenter.onSearchMovieIconClicked(searchEditText.getText().toString()));

        searchEditText = rootView.findViewById(R.id.searchMovieEditTextId);

        recyclerView = rootView.findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        searchAdapter = new SearchAdapter();
        recyclerView.setAdapter(searchAdapter);

        final FloatingActionButton saveFab = rootView.findViewById(R.id.floatingActionBtnId);
        saveFab.setOnClickListener(saveButton -> presenter.onSaveButtonClicked(searchAdapter.getCurrentList()));

        progressBar = rootView.findViewById(R.id.progressBarId);

        presenter.onLoadRandomMovies();

        getActivity().setTitle("Search Movie");

        return rootView;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setEnabled(true);
    }

    @Override
    public void displayMovieList(List<Movie> movies) {
        searchAdapter.submitList(movies);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (presenter != null)
            presenter.onDestroy();
    }
}
