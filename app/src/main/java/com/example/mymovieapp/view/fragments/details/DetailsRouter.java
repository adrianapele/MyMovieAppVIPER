package com.example.mymovieapp.view.fragments.details;

import androidx.fragment.app.FragmentActivity;

public class DetailsRouter implements DetailsContract.Router {
    @Override
    public void navigateBackToFavoritesFragment(DetailsContract.View view) {
        DetailsFragment detailsFragment = (DetailsFragment) view;
        final FragmentActivity activity = detailsFragment.getActivity();
        activity.onBackPressed();
    }
}
