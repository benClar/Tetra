package com.android.benjaminclarke.tetra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.view.ViewGroup.LayoutParams;

public class Deck extends AppCompatActivity {

    private static int PLAYER_DECK_WIDTH = 10;
    private static int PLAYER_DECK_HEIGHT = 10;
    private CardChooser cardChooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);
        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.activity_deck);
        LinearLayout playerDeckView = (LinearLayout) getLayoutInflater().inflate(R.layout.player_deck, rootLayout, false);

        RelativeLayout cardChooserView = (RelativeLayout) getLayoutInflater().inflate(R.layout.card_chooser, rootLayout, false);

        RelativeLayout.LayoutParams cardChooserViewP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        cardChooserViewP.addRule(RelativeLayout.BELOW, R.id.chosen_deck);
        cardChooserViewP.addRule(RelativeLayout.END_OF, R.id.strut);
        cardChooserView.setLayoutParams(cardChooserViewP);

        LinearLayout chosenDeckView = (LinearLayout) getLayoutInflater().inflate(R.layout.chosen_deck, rootLayout, false);


        cardChooser = new CardChooser(cardChooserView);

        addPlayerCards(playerDeckView);
        rootLayout.addView(playerDeckView);
        rootLayout.addView(chosenDeckView);
        rootLayout.addView(cardChooserView);
    }

    private void addPlayerCards(LinearLayout playerDeck){
        // get all the players card types and add them to the deck for choosing
        Card card = new Card(); //TODO: Going to have to add cards in reliable order
        for(int r = 0; r < PLAYER_DECK_HEIGHT; r++){
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
            for(int c = 0; c < PLAYER_DECK_WIDTH; c++){
                row.addView(getCardImage(card));
            }
            playerDeck.addView(row);

        }
    }

    private ImageView getCardImage(Card c){
        // TODO: lookup and dynamically create card image based on card stats
        ImageView cardImage = new ImageView(this);
        cardImage.setImageResource(R.drawable.temp_card);
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);
        lp.setMargins(0, 10, 0, 10);
        cardImage.setLayoutParams(lp);
        cardImage.setId(c.getTypeId());
        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardChooser.addCard(v);
            }
        });
        return cardImage;
    }

}
