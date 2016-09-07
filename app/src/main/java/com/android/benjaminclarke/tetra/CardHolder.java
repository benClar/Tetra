package com.android.benjaminclarke.tetra;

import java.io.Console;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by benjaminclarke on 04/09/2016.
 */
public abstract class CardHolder implements Runnable {

    private Integer limit;
    protected Queue<Card> incomingCards;
    protected ArrayList<Card> heldCards;

    public CardHolder(int limit){
        this.limit = limit;
        this.incomingCards = new ConcurrentLinkedQueue<>();
        heldCards = new ArrayList<>();
    }

    public CardHolder(){
        this.limit = null;
        this.incomingCards = new ConcurrentLinkedQueue<>();
        heldCards = new ArrayList<>();
    }

    public boolean reachedLimit(){
        return this.limit == heldCards.size();
    }

    public void addCard(Card c){
        incomingCards.add(c);
    }


    protected void removeCard(Card c){
        heldCards.remove(c);
    }

    // tells cardholder what to do when it gets a card
    public void run(){

    }

}
