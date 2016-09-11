package com.android.benjaminclarke.tetra;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Queue;
import java.util.logging.Logger;

/**
 * Created by benjaminclarke on 04/09/2016.
 */
public class ChosenDeckCardHolder extends CardHolder implements CardObserver {

    private final static Logger logger = Logger.getLogger(CardHolder.class.getName());
    private LinearLayout chosenDeckView;
    private Activity activity;
    private CardHolder outgoingCards;

    public LinearLayout getChosenDeckView() {
        return chosenDeckView;
    }

    public ChosenDeckCardHolder(int limit, Activity activity, RelativeLayout rootLayout) {
        super(limit);
        this.chosenDeckView = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.chosen_deck, rootLayout, false);
        this.activity = activity;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
                final Card c = incomingCards.poll(); // check if card has been selected and sent over
                if(c != null){
                    c.registerObserver(this);
                    logger.info("New card to be added to chosen");
                    heldCards.add(c);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                            c.cardView.setLayoutParams(lp);
                            c.cardView.setPadding(0,0,0,0);
                            chosenDeckView.addView(c.cardView);
                        }
                    });
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void removeCard(Card c){
        logger.info("Removing Card from chosen deck");
        View cardView = chosenDeckView.findViewById(c.getId());
        ((ViewGroup) cardView.getParent()).removeView(cardView);
        c.deRegisterObserver(this);
        this.heldCards.remove(c);
        this.outgoingCards.addCard(c);
    }

    public void cardSwipe(Card card, Direction d) {
        logger.info("Deck Card Swiped");
        if(d == Direction.UP || d == Direction.DOWN){
            removeCard(card);
        }

    }

    public void cardTap(Card card, MotionEvent e){

    }

    public void setOutgoingCardsQueue(CardHolder outgoingCards){
        this.outgoingCards = outgoingCards;
    }
}
