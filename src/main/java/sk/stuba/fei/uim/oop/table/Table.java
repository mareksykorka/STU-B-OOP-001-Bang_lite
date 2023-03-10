package sk.stuba.fei.uim.oop.table;

import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;
import java.util.Collections;

public class Table {
    private ArrayList<Card> deck;
    private ArrayList<Card> discardPile;

    public Table() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < 30; i++) {
            cards.add(new Bang());
        }
        for (int i = 0; i < 15; i++) {
            cards.add(new Missed());
        }
        for (int i = 0; i < 8; i++) {
            cards.add(new Beer());
        }
        for (int i = 0; i < 6; i++) {
            cards.add(new CatBalou());
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new Stagecoach());
        }
        cards.add(new Indians());
        cards.add(new Indians());
        cards.add(new Barrel());
        cards.add(new Barrel());
        cards.add(new Dynamite());
        cards.add(new Prison());
        cards.add(new Prison());
        cards.add(new Prison());

        Collections.shuffle(cards);

        this.deck = cards;
        this.discardPile = new ArrayList<Card>();
    }

    public ArrayList<Card> drawCards(int numberOfCards) {
        ArrayList<Card> outputCards = new ArrayList<Card>();
        Card newCard;
        for (int i = 0; i < numberOfCards; i++){
            if((newCard = this.drawCards()) == null) {
                break;
            }
            outputCards.add(newCard);
        }
        return outputCards;
    }
    private Card drawCards() {
        Card outputCard;
        if(this.deck.isEmpty()) {
            return (this.reffillDeck() ? this.drawCards() : null);
        }
        outputCard = deck.get(0);
        this.deck.remove(0);
        return outputCard;
    }

    public void discardCard(Card card) {
        this.discardPile.add(card);
    }
    public void discardCards(ArrayList<Card> cards) {
        this.discardPile.addAll(cards);
    }

    public boolean reffillDeck(){
        if (!(discardPile.isEmpty())) {
            Collections.shuffle(this.discardPile);
            deck.addAll(discardPile);
            discardPile.clear();
            return true;
        }

        System.out.println("There are no more cards left on the table, neither in the deck nor in the discard pile.");
        return false;
    }
}
