package com.android.benjaminclarke.tetra;

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
public class CardChooser extends android.view.GestureDetector.SimpleOnGestureListener implements CardObserver{

    final private static int SELECTED_CARD_PADDING_PX = 20;

    private ArrayList<Card> availableOwnedCards;
    private RelativeLayout cardChooserView;
    private Context c;
    private GestureDetector gestureDetector;
    private View.OnTouchListener gestureListener;
    private final static Logger logger = Logger.getLogger(CardChooser.class.getName());
    private CardHolder chosenCards;
    private int selectedCardIndex; // element of currently selected card

    public CardChooser(RelativeLayout cardChooserView, Context context, CardHolder chosenCards)    {
        availableOwnedCards = new ArrayList<>();
        this.cardChooserView = cardChooserView;
        this.c=context;

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

    public void loadCardsOfType(View v){
        //TODO: Using the id, get all cards and load them up using c.getId().
        //Then select the first one as the selected card and register to it.
        Card card = new Card(c); // for now.  Here is where we're going to load card objects with correct images.
        availableOwnedCards.add(card);
        selectCard(card);
    }

    private void selectCard(Card card){
        //TODO: need to do the removing etc. of currently selected card
        card.registerObserver(this);
        logger.info("Setting new card");
        View selectedCard = cardChooserView.findViewById(R.id.selected_card);
        ((ViewGroup) selectedCard.getParent()).removeView(selectedCard);
        configureSelectedCard(card.cardView);

        cardChooserView.addView(card.cardView);
//        newC.setOnTouchListener(gestureListener);
    }

    private void configureSelectedCard(ImageView cardView){

        cardView.setId(R.id.selected_card);
        int p = MyUtils.dp(SELECTED_CARD_PADDING_PX, c);
        cardView.setPadding(p, p, p, p);
    }

    @Override
    public boolean onDown(MotionEvent e)
    {
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

    private void removeSelectedCard(Card card){
        card.deRegisterObserver(this);
        availableOwnedCards.remove(selectedCardIndex);
        if(selectedCardIndex == availableOwnedCards.size()){
            selectPrevCard();
        }
    }

    public void cardSwipe(Card card, MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() > e2.getX()){
            logger.info("Swipe right to left");

        } else  {
            logger.info("Swipe left to right");
        }
    }

    public void cardTap(Card card, MotionEvent e){
        // Remove selected card from card chooser
        // and add it to chosen cards
        logger.info("Tap: Card Chosen");
        if(!this.chosenCards.reachedLimit()){
            this.chosenCards.addCard(card);
//            this.removeSelectedCard(card);

        }
    }

    public void cardToss(Card card){

    }
}
