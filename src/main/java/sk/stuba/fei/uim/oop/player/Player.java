package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Card;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int lives;
    private ArrayList<Card> cardsOnHand;
    private ArrayList<Card> cardsOnTable;

    public Player(String name) {
        this.name = name;
        this.lives = 4;
        this.cardsOnHand = new ArrayList<Card>();
        this.cardsOnTable = new ArrayList<Card>();
    }

    public void setCardsOnHand(ArrayList<Card> cardsOnHand) {
        this.cardsOnHand = cardsOnHand;
    }
}
