package com.android.benjaminclarke.tetra;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Queue;
import java.util.logging.Logger;

/**
 * Created by benjaminclarke on 04/09/2016.
 */
public class ChosenDeckCardHolder extends CardHolder implements CardObserver {

    private final static Logger logger = Logger.getLogger(CardHolder.class.getName());
    private LinearLayout chosenDeckView;
    private Activity activity;
    private Queue<Card> outgoingCards;

    public ChosenDeckCardHolder(int limit, LinearLayout chosenDeckView, Activity activity) {
        super(limit);
        this.chosenDeckView = chosenDeckView;
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
                            chosenDeckView.addView(c.cardView);
                        }
                    });
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void removeCard(Card c){
        super.removeCard(c);
        View cardView = chosenDeckView.findViewById(c.getId());
        ((ViewGroup) cardView.getParent()).removeView(cardView);
    }

    public void cardSwipe(Card card, Direction d) {
        logger.info("Deck Card Swiped");
        if(d == Direction.UP || d == Direction.DOWN){
            this.heldCards.remove(card);
            this.addCard(card);
        }

    }

    public void cardTap(Card card, MotionEvent e){

    }

    public void setOutgoingCardsQueue(Queue<Card> outgoingCards){
        this.outgoingCards = outgoingCards;
    }
}
