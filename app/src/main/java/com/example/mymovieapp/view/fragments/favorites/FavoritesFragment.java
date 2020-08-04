package com.example.mymovieapp.view.fragments.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mymovieapp.R;
import com.example.mymovieapp.data.model.Movie;
import com.example.mymovieapp.data.repository.MovieRepository;
import com.example.mymovieapp.view.MyRecyclerView;
import com.example.mymovieapp.view.adapters.FavoritesAdapter;

import java.util.List;

public class FavoritesFragment extends Fragment implements FavoritesContract.View, FavoritesAdapter.RecyclerViewClickListener {

    public static final String TAG = "favoritesFragmentTag";

    private FavoritesPresenter favoritesPresenter;
    private FavoritesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setupPresenter();
    }

    private void setupPresenter()
    {
        final MovieRepository movieRepository = new MovieRepository(getActivity().getApplication());
        final FavoritesInteractor interactor = new FavoritesInteractor(movieRepository);
        final FavoritesRouter router = new FavoritesRouter();
        favoritesPresenter = new FavoritesPresenter(this, interactor, router);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        MyRecyclerView recyclerView = rootView.findViewById(R.id.favoritesRecyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        final RelativeLayout emptyView = rootView.findViewById(R.id.emptyViewId);
        recyclerView.setEmptyView(emptyView);

        adapter = new FavoritesAdapter();
        adapter.setOnRecyclerViewItemClickListener(this);
        recyclerView.setAdapter(adapter);

        favoritesPresenter.onLoadSavedMovies();

        getActivity().setTitle("Favorites");

        return rootView;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void displayMovieList(List<Movie> movies) {
        if (adapter != null)
            adapter.submitList(movies);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecyclerViewItemClick(View view, Movie movie) {
        favoritesPresenter.onShowDetailsPressed(view, movie.getId());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        inflater.inflate(R.menu.delete_all_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.delete_all_favorites_movie)
        {
            favoritesPresenter.onDeleteAllMoviesPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (favoritesPresenter != null)
            favoritesPresenter.onDestroy();
    }
}
