package com.android.benjaminclarke.tetra;

import android.content.Context;
import android.media.Image;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by benjaminclarke on 03/09/2016.
 */
public class CardChooser extends android.view.GestureDetector.SimpleOnGestureListener {

    private ArrayList<Card> availableOwnedCards;
    private RelativeLayout cardChooserView;
    private Context context;
    private GestureDetector gestureDetector;
    private View.OnTouchListener gestureListener;
    private final static Logger logger = Logger.getLogger(CardChooser.class.getName());
    private CardHolder chosenCards;
    private int selectedCardIndex; // element of currently selected card

    public CardChooser(RelativeLayout cardChooserView, Context context, CardHolder chosenCards)    {
        availableOwnedCards = new ArrayList<>();
        this.cardChooserView = cardChooserView;
        this.context=context;

        gestureDetector = new GestureDetector(context, this);
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        };
        this.chosenCards = chosenCards;
        this.selectedCardIndex = 0;

    }

    public void loadCardsOfType(View c){
        availableOwnedCards.add(new Card()); //TODO: This needs to be all the cards of this type in a loop

        // Set to the first card
        ImageView newC = (ImageView) cardChooserView.findViewById(R.id.selected_card);
        newC.setImageResource(R.drawable.temp_card_pink);
        newC.setOnTouchListener(gestureListener);
        //TODO: Go find all the cards using view ID and add them.
        c.getId();
    }

    @Override
    public boolean onDown(MotionEvent e)
    {
        return true;
    }


    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        System.out.println(e1.getX());
        System.out.println(e2.getX());
        if (e1.getX() > e2.getX()){
            logger.info("Swipe right to left");

        } else  {
            logger.info("Swipe left to right");
        }
        return true;
    }

    private Card selectedCard(){
        return availableOwnedCards.get(selectedCardIndex);
    }

    private void selectNextCard(){
        if (selectedCardIndex < availableOwnedCards.size() - 2) {
            // increment if there is one last card to get
            selectedCardIndex += 1;

        }
    }

    private void selectPrevCard(){
        if (selectedCardIndex > 0){
            selectedCardIndex -= 1;
        }
    }

    private void removeSelectedCard(){
        availableOwnedCards.remove(selectedCardIndex);
        if(selectedCardIndex == availableOwnedCards.size()){
            selectPrevCard();
        }
    }

    public boolean onSingleTapConfirmed(MotionEvent e){
        // Remove selected card from card chooser
        // and add it to chosen cards
        logger.info("Tap: Card Chosen");
        if(this.chosenCards.reachedLimit() == false){
            this.chosenCards.addCard(selectedCard());
        }
//        this.removeSelectedCard();
        return true;
    }
}
