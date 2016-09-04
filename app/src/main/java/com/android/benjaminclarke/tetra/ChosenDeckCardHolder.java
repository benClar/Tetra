package com.android.benjaminclarke.tetra;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.logging.Logger;

/**
 * Created by benjaminclarke on 04/09/2016.
 */
public class ChosenDeckCardHolder extends CardHolder implements CardObserver {

    private final static Logger logger = Logger.getLogger(CardHolder.class.getName());
    private LinearLayout chosenDeckView;
    private Activity activity;

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
                Card c = incomingCards.poll(); // check if card has been selected and sent over
                if(c != null){
                    c.registerObserver(this);
                    logger.info("New card to be added to chosen");
                    heldCards.add(c);
                    final ImageView newC = new ImageView(chosenDeckView.getContext());
                    newC.setImageResource(R.drawable.temp_card_pink);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    newC.setLayoutParams(lp);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            chosenDeckView.addView(newC);
                        }
                    });

                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    public void cardSwipe(Card card, MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

    }

    public void cardTap(Card card, MotionEvent e){

    }

    public void cardToss(Card card){
        logger.info("remove card from chosen deck");
    }

}
