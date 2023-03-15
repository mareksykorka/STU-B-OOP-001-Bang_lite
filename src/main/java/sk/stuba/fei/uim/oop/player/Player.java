package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.utility.TxtModif;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int lives;
    private ArrayList<Card> cardsOnHand;
    private ArrayList<Card> cardsOnTable;
    private Deck deck;

    public Player(String name, Deck deck) {
        this.name = name;
        this.lives = 4;
        this.cardsOnHand = new ArrayList<Card>();
        this.cardsOnTable = new ArrayList<Card>();
        this.deck = deck;
    }


    public String getName(){
        return TxtModif.ANSI_BOLD + this.name + TxtModif.ANSI_RESET;
    }


    public boolean isAlive(){
        return (this.lives > 0);
    }
    public String aliveStatus(){
        if(this.lives > 0) {
            String outString = TxtModif.ANSI_DARK_GREEN + "ALIVE " + TxtModif.ANSI_DARK_RED;
            for (int i = 0; i < this.lives; i++) {
                outString += TxtModif.UNICODE_HEART;
            }
            outString += " (" + this.lives + ")" + TxtModif.ANSI_RESET;
            return outString;
        }
        return TxtModif.ANSI_BRIGHT_BLACK + "DEAD" + TxtModif.ANSI_RESET;
    }
    public boolean isTurnEndAllowed() {
        return (this.cardsOnHand.size() <= this.lives);
    }

    public void addLives(int numberOfLives){
        for (int i = 0; i < numberOfLives; i++) {
            this.addLives();
        }
    }
    private void addLives(){
        this.lives++;
    }

    public boolean removeLives(int numberOfLives){
        for (int i = 0; i < numberOfLives; i++) {
            this.removeLives();
        }
        return this.isAlive();
    }
    private void removeLives(){
        this.lives--;
        if(this.lives <= 0) {
            this.lives = 0;
            this.deck.playerDeath(this);
        }
    }



    public void setCardsOnHand(ArrayList<Card> cards) {
        this.cardsOnHand.addAll(cards);
    }
    public void setCardsOnHand(Card card) {
        this.cardsOnHand.add(card);
    }

    public Card removeCardOnHand(int indexOfCard){
        return removeCardOnHand(this.cardsOnHand.get(indexOfCard));
    }
    public Card removeCardOnHand(Card card){
        this.cardsOnHand.remove(card);
        return card;
    }
    public ArrayList<Card> removeCardOnHand(){
        ArrayList<Card> returnArr = new ArrayList<Card>();
        returnArr.addAll(this.cardsOnHand);
        this.cardsOnHand.clear();
        return returnArr;
    }

    public ArrayList<Card> getCardsOnHand(){
        return this.cardsOnHand;
    }
    public int getCardsOnHandNumber() {
        return this.cardsOnHand.size();
    }
    public void showCardsOnHand() {
        if(this.getCardsOnHandNumber() > 0){
            for (int i = 0; i < this.getCardsOnHandNumber(); i++) {
                System.out.println("\t"+ (i+1) + ". " + this.cardsOnHand.get(i).getName());
            }
        } else {
            System.out.println("\tYou don't have any cards.");
        }
    }



    public void setCardsOnTable(ArrayList<Card> cards) {
        this.cardsOnTable.addAll(cards);
    }
    public void setCardsOnTable(Card card) {
        this.cardsOnTable.add(card);
    }

    public Card removeCardOnTable(int indexOfCard){
        return this.removeCardOnHand(this.cardsOnTable.get(indexOfCard));
    }
    public Card removeCardOnTable(Card card){
        this.cardsOnTable.remove(card);
        return card;
    }
    public ArrayList<Card> removeCardOnTable(){
        ArrayList<Card> returnArr = new ArrayList<Card>();
        returnArr.addAll(this.cardsOnTable);
        this.cardsOnTable.clear();
        return returnArr;
    }

    public ArrayList<Card> getCardsOnTable(){
        return this.cardsOnTable;
    }
    public int getCardsOnTableNumber() {
        return this.cardsOnTable.size();
    }
    public void showCardsOnTable() {
        if(this.getCardsOnTableNumber() > 0){
            for (int i = 0; i < this.getCardsOnTableNumber(); i++) {
                System.out.println("\t" + (i+1) + ". " + this.cardsOnTable.get(i).getName());
            }
        } else {
            System.out.println("\tNo active cards.");
        }
    }



    public boolean checkCardHand(Class cardType, Deck deck){
        //TODO: Neviem co vlastne returnujem.
        for (Card card:this.cardsOnHand) {
            if(cardType.isInstance(card)) {
                return card.receivePlay(this, deck);
            }
        }
        return false;
    }
    public boolean checkCardTable(Class cardType, Deck deck){
        //TODO: Neviem co vlastne returnujem.
        for (Card card:this.cardsOnTable) {
            if(cardType.isInstance(card)) {
                return card.receivePlay(this, deck);
            }
        }
        return false;
    }
    public void useCard(int cardIndex, ArrayList<Player> alivePlayers, Deck deck) {
        //TODO: Neviem co vlastne returnujem.
        this.cardsOnHand.get(cardIndex).play(this, alivePlayers, deck);
    }
}
