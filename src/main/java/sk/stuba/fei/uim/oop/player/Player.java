package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private final String name;
    private int lives;
    private ArrayList<Card> cardsOnHand;
    private ArrayList<Card> cardsOnTable;
    public enum PrintType {
        SIMPLE,
        FULL
    }

    public Player(String name) {
        this.name = name;
        this.lives = 4;
        this.cardsOnHand = new ArrayList<Card>();
        this.cardsOnTable = new ArrayList<Card>();
    }

    public String getName(){
        return this.name;
    }


    // Methods working with lives - checking alive status, manipulating with lives
    public boolean isAlive(){
        return (this.lives > 0);
    }
    public String isAlive(PrintType type){
        if(this.lives > 0) {
            if (type == PrintType.SIMPLE) {
                return " Is Alive ";
            }
            if (type == PrintType.FULL) {
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
        if(lives < 0)
            lives = 0;
    }


    // Methods working wiht cards on hand - setting, removing, showing or getting
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
    public ArrayList<Card> getCardsOnHand(){
        return this.cardsOnHand;
    }
    public int getCardsOnHandNumber() {
        return cardsOnHand.size();
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


    // Methods working wiht cards on table - setting, removing, showing or getting
    public void setCardsOnTable(ArrayList<Card> cards) {
        this.cardsOnTable.addAll(cards);
    }
    public void setCardsOnTable(Card card) {
        this.cardsOnTable.add(card);
    }
    public Card removeCardOnTable(int indexOfCard){
        return removeCardOnHand(this.cardsOnTable.get(indexOfCard));
    }
    public Card removeCardOnTable(Card card){
        this.cardsOnTable.remove(card);
        return card;
    }
    public ArrayList<Card> getCardsOnTable(){
        return this.cardsOnTable;
    }
    public int getCardsOnTableNumber() {
        return cardsOnTable.size();
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

    public void useCard(int cardIndex, Table table) {
        if(this.cardsOnHand.get(cardIndex).play(this)){
            table.discardCard(this.removeCardOnHand(cardIndex));
        } else {
            System.out.println("The Card could not be played.");
        }
    }

    public void checkDynamit(){
        for (Card card:this.cardsOnTable) {
            if (card instanceof Dynamite) {
                card.receivePlay(this);
            }
        }
    }
    public boolean checkPrison(){
        for (Card card:this.cardsOnTable) {
            if (card instanceof Prison) {
                return card.receivePlay(this);
            }
        }
        return false;
    }

    /*
    public boolean receiveIndians(Table table) {
        if (checkBang(table)) {
            return false;
        }
        this.removeLives(1);
        return true;
    }

    public boolean receiveCatBalou(){
        return false;
    }
    public boolean checkDynamit(Table table, Game game){
        for (Card card:this.cardsOnTable) {
            if (card instanceof Dynamite) {
                if ((randomGenerator.nextInt(8) + 1) == 1) {
                    this.removeLives(3);
                    table.discardCard(this.removeCardOnTable(this.cardsOnTable.indexOf(card)));
                    return true;
                }
                Player prevPlayer = game.getPlayerByIndex(game.prevPlayer());
                prevPlayer.setCardsOnTable(this.removeCardOnTable(card));
                return false;
            }
        }
        return false;
    }
    private boolean checkBang(Table table) {
        for (Card card:this.cardsOnHand) {
            if(card instanceof Bang){
                table.discardCard(this.removeCardOnHand(this.cardsOnHand.indexOf(card)));
                return true;
            }
        }
        return false;
    }*/
}
