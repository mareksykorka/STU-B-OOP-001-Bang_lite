package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.table.Table;
import java.util.ArrayList;

public class Player {
    private final String name;
    private int lives;
    private ArrayList<Card> cardsOnHand;
    private ArrayList<Card> cardsOnTable;

    public Player(String name, Table table) {
        this.name = name;
        this.lives = 4;
        this.cardsOnHand = new ArrayList<Card>();
        this.setCardsOnHand(table.drawCards(4));
        this.cardsOnTable = new ArrayList<Card>();
    }

    public void setCardsOnHand(ArrayList<Card> cardsOnHand) {
        this.cardsOnHand.addAll(cardsOnHand);
    }

    public void showCarsOnTable() {
        if(cardsOnTable.size() > 0){
            for (int i = 0; i < cardsOnTable.size(); i++) {
                System.out.println("\t"+ (i+1) + ". " + cardsOnTable.get(i).getName());
            }
        } else {
            System.out.println("\t - No active cards on table.");
        }
    }

    public void showCarsOnHand() {
        if(cardsOnHand.size() > 0){
            for (int i = 0; i < cardsOnHand.size(); i++) {
                System.out.println("\t"+ (i+1) + ". " + cardsOnHand.get(i).getName());
            }
        } else {
            System.out.println("\t - No cards on hand.");
        }
    }

    public void playCard(int cardIndex, Table table)
    {
        this.cardsOnHand.get(cardIndex).play();
        this.throwAway(cardIndex, table);
    }

    public String getName(){
        return this.name;
    }

    public boolean isAlive(){
        if(this.lives > 0){
            return true;
        } else {
            return false;
        }
    }

    public String showAliveStatus(){
        if(this.lives > 0){
            return "Alive";
        } else {
            return "Dead";
        }
    }

    public boolean checkCards() {
        if(cardsOnHand.size() > lives){
            return true;
        } else {
            return false;
        }
    }

    public void throwAway(int input, Table table) {
        table.discardCard(this.cardsOnHand.get(input));
        cardsOnHand.remove(input);
    }
}
