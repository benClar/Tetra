package com.android.benjaminclarke.tetra;

/**
 * Created by benjaminclarke on 03/09/2016.
 */


public class Card {

    private String name;
    private int id;
    private int arrows;
    private CardElement element;
    private int level;
    private int attack;
    private int magicAttack;
    private int defence;
    private int magicDefence;
    private CardType type;

    public Card(String name, int id, int arrows, int level, CardElement element, int attack, int magicAttack, int defense, int magicDefence, CardType type){
        this.name = name;
        this.id= id;
        this.arrows = arrows;
        this.level = level;
        this.element = element;
        this.attack = attack;
        this.magicAttack = magicAttack;
        this.defence = defense;
        this.magicDefence = magicDefence;
        this.type = type;
    }

    public Card(){
        // Produces default card
        this.name = "Default";
        this.id= 999;
        this.arrows = 0b11111111;
        this.level = 1;
        this.element = CardElement.EARTH;
        this.attack = 10;
        this.magicAttack = 10;
        this.defence = 10;
        this.magicDefence = 10;
        this.type = CardType.ATTACK;
    }

    public int getId() {
        return id;
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
}
