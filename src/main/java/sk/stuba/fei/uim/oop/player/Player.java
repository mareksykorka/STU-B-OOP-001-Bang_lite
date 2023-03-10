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

    public String getName(){
        return this.name;
    }

    public boolean isAlive(){
        return (this.lives > 0);
    }
    //TODO: prerob input nasledujucej metody na pouzitelnejsi format
    public String isAlive(String format){
        if(this.lives > 0) {
            if (format.equals("Simple")) {
                return " Is Alive ";
            }
            if (format.equals("Full")) {
                return " Is Alive with " + this.lives + " lives.";
            }
        }
        return " Is Dead ";
    }
    public boolean isTurnEndAllowed() {
        return (cardsOnHand.size() <= lives);
    }
    public void addLives(int numberOfLives){
        for (int i = 0; i < numberOfLives; i++) {
            addLives();
        }
    }
    private void addLives(){
        lives++;
    }
    public void removeLives(int numberOfLives){
        for (int i = 0; i < numberOfLives; i++) {
            removeLives();
        }
    }
    private void removeLives(){
        lives--;
    }

    public void setCardsOnHand(ArrayList<Card> cards) {
        this.cardsOnHand.addAll(cards);
    }
    public void setCardsOnHand(Card card) {
        this.cardsOnHand.add(card);
    }
    public ArrayList<Card> getCardsOnHand(){
        return this.cardsOnHand;
    }
    public Card removeCardOnHand(int indexOfCard){
        Card card = this.cardsOnHand.get(indexOfCard);
        return removeCardOnHand(card);
    }
    public Card removeCardOnHand(Card card){
        this.cardsOnHand.remove(card);
        return card;
    }

    public void setCardsOnTable(ArrayList<Card> cards) {
        this.cardsOnTable.addAll(cards);
    }
    public void setCardsOnTable(Card card) {
        this.cardsOnTable.add(card);
    }
    public ArrayList<Card> getCardsOnTable(){
        return this.cardsOnTable;
    }
    public Card removeCardOnTable(int indexOfCard){
        Card card = this.cardsOnTable.get(indexOfCard);
        return removeCardOnHand(card);
    }
    public Card removeCardOnTable(Card card){
        this.cardsOnTable.remove(card);
        return card;
    }

    public void showCardsOnTable() {
        if(cardsOnTable.size() > 0){
            for (int i = 0; i < cardsOnTable.size(); i++) {
                System.out.println("\t"+ (i+1) + ". " + cardsOnTable.get(i).getName());
            }
        } else {
            System.out.println("\tThis player does not have any active cards.");
        }
    }
    public void showCardsOnHand() {
        if(cardsOnHand.size() > 0){
            for (int i = 0; i < cardsOnHand.size(); i++) {
                System.out.println("\t"+ (i+1) + ". " + cardsOnHand.get(i).getName());
            }
        } else {
            System.out.println("\tYou don't have any cards.");
        }
    }

    /*
    public void useCard(int cardIndex, Table table)
    {
        this.cardsOnHand.get(cardIndex).play();
        this.throwAway(cardIndex, table);
    }

    public void throwAway(int input, Table table) {
        table.discardCard(this.cardsOnHand.get(input));
        cardsOnHand.remove(input);
    }*/
}
