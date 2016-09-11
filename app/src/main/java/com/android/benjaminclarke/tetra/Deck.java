package com.android.benjaminclarke.tetra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.view.ViewGroup.LayoutParams;

import java.util.logging.Logger;

public class Deck extends AppCompatActivity {

    private final static Logger logger = Logger.getLogger(Deck.class.getName());
    private static int PLAYER_DECK_WIDTH = 10;
    private static int PLAYER_DECK_HEIGHT = 10;
    private CardChooser cardChooser; // UI for selecting card of chosen type
    private ChosenDeckCardHolder chosenDeck; // cards that player had chosen
    private LinearLayout playerDeckView; // all types of cards to choose

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);
        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.activity_deck);

        playerDeckView = (LinearLayout) getLayoutInflater().inflate(R.layout.player_deck, rootLayout, false);

        chosenDeck = new ChosenDeckCardHolder(5, this, rootLayout);
        Thread chosenDeckThread = new Thread(chosenDeck);
        chosenDeckThread.start();

        cardChooser = new CardChooser(this, rootLayout, chosenDeck);
        Thread cardChooserThread = new Thread(chosenDeck);
        cardChooserThread.start();

        addPlayerCards(playerDeckView);
        rootLayout.addView(playerDeckView);
        rootLayout.addView(cardChooser.getCardChooserView());
        rootLayout.addView(chosenDeck.getChosenDeckView());
    }

    private void addPlayerCards(LinearLayout playerDeck){
        // get all the players card types and add them to the deck for choosing
         //TODO: Going to have to add cards in reliable order
        for(int r = 0; r < PLAYER_DECK_HEIGHT; r++){
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
            for(int c = 0; c < PLAYER_DECK_WIDTH; c++){
                row.addView(getCardImage(10));
            }
            playerDeck.addView(row);

        }
    }

    private ImageView getCardImage(int cardTypeId){
        // TODO: lookup image for card type

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);
        lp.setMargins(0, 10, 0, 10);
        ImageView cardView = new ImageView(this);
        cardView.setImageResource(R.drawable.temp_card);
        cardView.setLayoutParams(lp);
        cardView.setId(cardTypeId);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardChooser.loadCardsOfType(v);
            }
        }); //TODO: Use observable for this
        return cardView;
    }

}
