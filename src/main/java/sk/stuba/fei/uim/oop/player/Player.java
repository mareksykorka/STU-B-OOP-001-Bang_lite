package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;

public class Player {
    private String name;
    private int lives;
    private ArrayList<Card> cardsOnHand;
    private ArrayList<Card> cardsOnTable;
    private Deck deck;
    private String statusMessage;

    public Player(String name, Deck deck) {
        this.name = name;
        this.lives = 4;
        this.cardsOnHand = new ArrayList<>();
        this.cardsOnTable = new ArrayList<>();
        this.deck = deck;
        this.statusMessage = "";
    }

    public String getName(){
        return TxtDef.ANSI_BOLD + ((this.isAlive())?(TxtDef.ANSI_DARK_PURPLE):(TxtDef.ANSI_GREY)) + "'" +
                this.name + "'" + TxtDef.ANSI_RESET;
    }
    public String getStatusMessage() {
        String outString = this.statusMessage;
        this.statusMessage = "";
        return outString;
    }
    public void setStatusMessage(String message) {
        this.statusMessage += message + "\n";
    }

    public boolean isAlive() {
        return (this.lives > 0);
    }
    public String isAliveToString(){
        if(this.lives > 0) {
            String outString = TxtDef.ANSI_DARK_GREEN + "ALIVE " + TxtDef.ANSI_DARK_RED;
            for (int i = 0; i < this.lives; i++) {
                outString += TxtDef.UNICODE_HEART;
            }
            outString += " (" + this.lives + ")" + TxtDef.ANSI_RESET;
            return outString;
        }
        return TxtDef.ANSI_GREY + "DEAD" + TxtDef.ANSI_RESET;
    }
    public boolean isTurnEndAllowed() {
        return (this.cardsOnHand.size() <= this.lives);
    }
    public void addLives(int numberOfLives) {
        for (int i = 0; i < numberOfLives; i++) {
            this.lives++;
        }
    }
    public void removeLives(int numberOfLives) {
        for (int i = 0; i < numberOfLives; i++) {
            this.lives--;
            if(this.lives <= 0) {
                this.lives = 0;
                this.deck.playerDeath(this);
            }
        }
    }

    public void setCardsOnHand(ArrayList<Card> cards) {
        this.cardsOnHand.addAll(cards);
    }
    public Card removeCardsOnHand(Card card) {
        this.cardsOnHand.remove(card);
        return card;
    }
    public ArrayList<Card> removeCardsOnHand(){
        ArrayList<Card> returnArr = new ArrayList<Card>(this.cardsOnHand);
        this.cardsOnHand.clear();
        return returnArr;
    }
    public ArrayList<Card> getCardsOnHand(){
        return this.cardsOnHand;
    }
    public int getCardsOnHandNumber() {
        return this.cardsOnHand.size();
    }
    public String cardsOnHandToString() {
        String outString = "";
        if(this.getCardsOnHandNumber() > 0){
            for (int i = 0; i < this.getCardsOnHandNumber(); i++) {
                outString += "\t" + (i+1) + ". " + this.cardsOnHand.get(i).getName() + "\n";
            }
        } else {
            outString += "\tYou don't have any cards." + "\n";
        }
        return outString;
    }

    public void setCardsOnTable(Card card) {
        this.cardsOnTable.add(card);
    }
    public Card removeCardOnTable(Card card) {
        this.cardsOnTable.remove(card);
        return card;
    }
    public ArrayList<Card> removeCardOnTable() {
        ArrayList<Card> returnArr = new ArrayList<Card>(this.cardsOnTable);
        this.cardsOnTable.clear();
        return returnArr;
    }
    public ArrayList<Card> getCardsOnTable() {
        return this.cardsOnTable;
    }
    public int getCardsOnTableNumber() {
        return this.cardsOnTable.size();
    }
    public String showCardsOnTable() {
        String outString = "";
        if(this.getCardsOnTableNumber() > 0) {
            for (int i = 0; i < this.getCardsOnTableNumber(); i++) {
                outString += "\t" + (i+1) + ". " + this.cardsOnTable.get(i).getName()  + "\n";
            }
        } else {
            outString += "\tNo active cards.\n";
        }
        return outString;
    }

    public boolean checkCardHand(Class cardType, Deck deck) {
        for (Card card : this.cardsOnHand) {
            if(cardType.isInstance(card)) {
                return card.receivePlay(this, deck);
            }
        }
        return false;
    }
    public boolean checkCardTable(Class cardType, Deck deck) {
        for (Card card:this.cardsOnTable) {
            if(cardType.isInstance(card)) {
                return card.receivePlay(this, deck);
            }
        }
        if(cardType == Prison.class) {
            return true;
        }
        return false;
    }
    public boolean checkCardTable(Class cardType) {
        for (Card card:this.cardsOnTable) {
            if(cardType.isInstance(card)) {
                return true;
            }
        }
        return false;
    }

    public void useCard(int cardIndex, ArrayList<Player> enemyPlayers, Deck deck) {
        this.cardsOnHand.get(cardIndex).play(this, enemyPlayers, deck);
    }
}
