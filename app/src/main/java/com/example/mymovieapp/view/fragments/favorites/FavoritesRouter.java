package com.example.mymovieapp.view.fragments.favorites;


import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mymovieapp.R;
import com.example.mymovieapp.view.fragments.details.DetailsFragment;

public class FavoritesRouter implements FavoritesContract.Router {

    @Override
    public void navigateToDetailsFragment(View view, int movieId) {
        final AppCompatActivity activity = (AppCompatActivity) view.getContext();

        final FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment detailsFragment = fragmentManager.findFragmentByTag(DetailsFragment.DETAILS_FRAGMENT_TAG);

        if (detailsFragment == null)
            detailsFragment = DetailsFragment.newInstance(movieId);

        fragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, detailsFragment, DetailsFragment.DETAILS_FRAGMENT_TAG)
                .addToBackStack(DetailsFragment.DETAILS_FRAGMENT_TAG)
                .commit();
    }
}
