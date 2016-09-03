package com.android.benjaminclarke.tetra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Deck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);
    }

    private void loadPlayerCards(){
        Card c = new Card();
    }

}
