package com.android.benjaminclarke.tetra;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by benjaminclarke on 03/09/2016.
 */
public class CardChooser extends CardHolder implements CardObserver{

    final private static int SELECTED_CARD_PADDING_PX = 20;

    private RelativeLayout cardChooserView;
    private Context c;
    private final static Logger logger = Logger.getLogger(CardChooser.class.getName());
    private ChosenDeckCardHolder chosenCards;
    private int selectedCardIndex; // element of currently selected card
    private Card selectedCard;
    public CardChooser(RelativeLayout cardChooserView, Context context, ChosenDeckCardHolder chosenCards)    {
        super();
        this.cardChooserView = cardChooserView;
        this.c=context;
        this.chosenCards = chosenCards;
        this.selectedCardIndex = 0;
        this.chosenCards.setOutgoingCardsQueue(this.incomingCards);
        this.selectedCard = new Card(c); // default card
        selectCard(selectedCard);
    }

    public void loadCardsOfType(View v){
        //TODO: Using the id, get all cards and load them up using c.getId().
        //Then select the first one as the selected card and register to it.
        Card card = new Card(c); // for now.  Here is where we're going to load card objects with correct images.
        selectCard(card);
    }

    private void selectCard(Card newCard){
        //TODO: need to do the removing etc. of currently selected card
        logger.info("Setting new card");
        newCard.registerObserver(this);
        addCard(newCard);
        configureSelectedCard(newCard.cardView);
        selectedCard = newCard;
        cardChooserView.addView(newCard.cardView);
        removeCard(newCard);
//        newC.setOnTouchListener(gestureListener);
    }

    private void configureSelectedCard(ImageView cardView){
        int p = MyUtils.dp(SELECTED_CARD_PADDING_PX, c);
        cardView.setPadding(p, p, p, p);
    }

    private Card selectedCard(){
        return this.selectedCard;
    }

    private void removeSelectedCard(){
        if(this.selectedCard != null) {
            selectedCard.deRegisterObserver(this);
            View selectedCardView = cardChooserView.findViewById(selectedCard().getId());
            ((ViewGroup) selectedCardView.getParent()).removeView(selectedCardView);
            removeCard(selectedCard);
            selectedCard = null;
        }
    }

    public void cardSwipe(Direction direction) {

    }

    public void cardTap(Card card, MotionEvent e){
        // Remove selected card from card chooser
        // and add it to chosen cards
        logger.info("Tap: Card Chosen");
        if(!this.chosenCards.reachedLimit()){
            this.removeSelectedCard();
            this.chosenCards.addCard(card);
        }
        //TODO try to load the next card of same type
    }

    @Override
    public void cardSwipe(Card c, Direction direction) {

    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
                super.run();
//                Card c = incomingCards.poll(); // check if card has been selected and sent over
//                if(c != null){
//                    c.registerObserver(this);
//                    logger.info("New card to be added to chosen");
//                    heldCards.add(c);
//                    final ImageView newC = new ImageView(chosenDeckView.getContext());
//                    newC.setImageResource(R.drawable.temp_card_pink);
//                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
//                    newC.setLayoutParams(lp);
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            chosenDeckView.addView(newC);
//                        }
//                    });
//
//                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
