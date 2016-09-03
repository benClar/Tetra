package com.android.benjaminclarke.tetra;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by benjaminclarke on 03/09/2016.
 */
public class CardChooser  {

    private ArrayList<Card> ownedCards;
    private RelativeLayout cardChooserView;
    private Context context;

    public CardChooser(RelativeLayout cardChooserView)    {
        ownedCards = new ArrayList<>();
        this.cardChooserView = cardChooserView;
    }

    public void addCard(View c){
        ImageView newC = (ImageView) cardChooserView.findViewById(R.id.selected_card);
        newC.setImageResource(R.drawable.temp_card_pink);
        //TODO: Go find all the cards using view ID and add them.
        c.getId();
    }
}
