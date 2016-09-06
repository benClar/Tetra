package com.android.benjaminclarke.tetra;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by benjaminclarke on 03/09/2016.
 */


public class Card extends android.view.GestureDetector.SimpleOnGestureListener {

    private String name;
    private int typeId;
    private int uniqueId;
    private int arrows;
    private CardElement element;
    private int level;
    private int attack;
    private int magicAttack;
    private int defence;
    private int magicDefence;
    private CardType type;
    private GestureDetector gestureDetector;
    private View.OnTouchListener  gestureListener;
    public ImageView cardView;
    private Context context;
    private final static Logger logger = Logger.getLogger(Card.class.getName());
    private ArrayList<CardObserver> observers;


    public Card(String name, int typeId, int id, int arrows, int level, CardElement element, int attack, int magicAttack, int defense, int magicDefence, CardType type, ImageView cardView, Context context){
        this.name = name;
        this.uniqueId= id;
        this.typeId = typeId;
        this.arrows = arrows;
        this.level = level;
        this.element = element;
        this.attack = attack;
        this.magicAttack = magicAttack;
        this.defence = defense;
        this.magicDefence = magicDefence;
        this.type = type;
        this.cardView = cardView;
        this.cardView.setId(this.uniqueId);
        this.context = context;
        gestureDetector = new GestureDetector(this.context, this);
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        cardView.setOnTouchListener(gestureListener);
        observers = new ArrayList<>();
    }


    public Card(Context context){
        // Produces default card
        this.name = "Default";
        this.uniqueId= new Random().nextInt(999999999);
        this.arrows = 0b11111111;
        this.level = 1;
        this.element = CardElement.EARTH;
        this.attack = 10;
        this.magicAttack = 10;
        this.defence = 10;
        this.magicDefence = 10;
        this.type = CardType.ATTACK;
        this.cardView = new ImageView(context);
        this.cardView.setImageResource(R.drawable.temp_card_pink);
        this.cardView.setId(this.uniqueId);
        this.context = context;
        gestureDetector = new GestureDetector(this.context, this);
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        cardView.setOnTouchListener(gestureListener);
        observers = new ArrayList<>();
    }

    public int getId() {
        return uniqueId;
    }

    public int getArrows() {
        return arrows;
    }

    public CardElement getElement() {
        return element;
    }

    public int getAttack() {
        return attack;
    }

    public int getMagicAttack() {
        return magicAttack;
    }

    public int getDefence() {
        return defence;
    }

    public int getMagicDefence() {
        return magicDefence;
    }

    public CardType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public boolean onDown(MotionEvent e)
    {
        return true;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        logger.info("Swipe on Card");
        Direction d = MyUtils.coordsToDirections(e1, e2);
        logger.info(d.toString());
        for(CardObserver observer : observers){
            observer.cardSwipe(this, d);
        }
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent e){
        logger.info("Tap on Card");
        for(CardObserver observer : observers){
            observer.cardTap(this, e);
        }
        return true;
    }

    public void registerObserver(CardObserver cardObserver){
        observers.add(cardObserver);
    }

    public void deRegisterObserver(CardObserver cardObserver){
        observers.remove(cardObserver);
    }

}
