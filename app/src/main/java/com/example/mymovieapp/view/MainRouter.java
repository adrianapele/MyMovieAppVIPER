package com.example.mymovieapp.view;

import android.content.Intent;
import android.net.Uri;

public class MainRouter implements MainContract.Router {

    @Override
    public void openIntent(MainContract.View view, String moviesToText) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:pele_adriana@yahoo.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "My Movie List");
        intent.putExtra(Intent.EXTRA_TEXT, "Hi there! Checkout my favorite movie list: " + System.getProperty("line.separator") + moviesToText);
        ((MainActivity) view).startActivity(intent);
    }
}
