package com.example.mymovieapp.view.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mymovieapp.R;
import com.example.mymovieapp.data.model.Movie;
import com.example.mymovieapp.data.network.RetrofitClient;
import com.example.mymovieapp.data.repository.MovieRepository;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment implements DetailsContract.View{

    public static final String DETAILS_FRAGMENT_TAG = "detailsFragmentTag";
    public static final String MOVIE_ID_ARG = "movieIdArg";

    private TextView titleTextView, descriptionTextView, releaseDateTextView, noteTextView;
    private ImageView movieImageView;

    private int movieId;
    private Movie loadedMovie;

    private DetailsPresenter detailsPresenter;

    public DetailsFragment() {}

    public static DetailsFragment newInstance(int movieId)
    {
        final DetailsFragment detailsFragment = new DetailsFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt(DetailsFragment.MOVIE_ID_ARG, movieId);

        detailsFragment.setArguments(bundle);

        return detailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            movieId = getArguments().getInt(MOVIE_ID_ARG);

        setupPresenter();
    }

    private void setupPresenter()
    {
        final MovieRepository movieRepository = new MovieRepository(getActivity().getApplication());
        final DetailsInteractor interactor = new DetailsInteractor(movieRepository);
        final DetailsRouter router = new DetailsRouter();

        detailsPresenter = new DetailsPresenter(this, interactor, router);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        titleTextView = rootView.findViewById(R.id.details_title_id);
        descriptionTextView = rootView.findViewById(R.id.details_description_id);
        releaseDateTextView = rootView.findViewById(R.id.details_release_date_value_id);
        noteTextView = rootView.findViewById(R.id.details_note_value_id);
        movieImageView = rootView.findViewById(R.id.details_image_id);

        detailsPresenter.loadMovieById(movieId);

        Button deleteButton = rootView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> {
            if (loadedMovie == null)
                return;

            detailsPresenter.onDeleteMoviePressed(loadedMovie);
        });

        getActivity().setTitle("Movie Details");

        return rootView;
    }

    @Override
    public void displayMovie(Movie movie) {
        this.loadedMovie = movie;
        if (loadedMovie != null)
        {
            titleTextView.setText(loadedMovie.getTitle());
            descriptionTextView.setText(loadedMovie.getDescription());
            releaseDateTextView.setText(loadedMovie.getReleaseDate());
            noteTextView.setText(loadedMovie.getNote());

            Picasso.get().load(RetrofitClient.IMAGE_URL + loadedMovie.getPosterImagePath()).into(movieImageView);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        detailsPresenter.onDestroy();
    }
}
