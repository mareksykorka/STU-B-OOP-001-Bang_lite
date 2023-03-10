package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Barrel;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.Dynamite;
import sk.stuba.fei.uim.oop.cards.Missed;
import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private final String name;
    private int lives;
    private ArrayList<Card> cardsOnHand;
    private ArrayList<Card> cardsOnTable;
    private Random randomGenerator;

    public Player(String name) {
        this.name = name;
        this.lives = 4;
        this.cardsOnHand = new ArrayList<Card>();
        this.cardsOnTable = new ArrayList<Card>();
        this.randomGenerator = new Random();
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
    public int getCardsOnHandNumber() {
        return cardsOnHand.size();
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
    public int getCardsOnTableNumber() {
        return cardsOnTable.size();
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


    public boolean receiveBang(Table table){
        if(checkBarrel()){
            return false;
        }
        if(checkMissed(table)){
            return false;
        }
        this.removeLives(1);
        return true;
    }
    public boolean receiveCatBalou(){
        return false;
    }
    public void checkDynamit(Table table, Game game){
        for (Card card:this.cardsOnTable) {
            if (card instanceof Dynamite) {
                if ((randomGenerator.nextInt(8) + 1) == 1) {
                    this.removeLives(1);
                    table.discardCard(this.removeCardOnTable(this.cardsOnHand.indexOf(card)));
                }
                Player prevPlayer = game.getPlayerByIndex(game.prevPlayer());
                prevPlayer.setCardsOnTable(this.removeCardOnTable(this.cardsOnHand.indexOf(card)));
            }
        }
    }


    private boolean checkBarrel() {
        for (Card card:this.cardsOnTable) {
            if(card instanceof Barrel){
                if ((randomGenerator.nextInt(4) + 1) == 1) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkMissed(Table table) {
        for (Card card:this.cardsOnHand) {
            if(card instanceof Missed){
                table.discardCard(this.removeCardOnHand(this.cardsOnHand.indexOf(card)));
                return true;
            }
        }
        return false;
    }

    public void useCard(int cardIndex, Table table, Game game)
    {
        if(this.cardsOnHand.get(cardIndex).play(this, table, game)){
            table.discardCard(this.removeCardOnHand(cardIndex));
        } else {
            System.out.println("The Card could not be played.");
        }
    }
}
