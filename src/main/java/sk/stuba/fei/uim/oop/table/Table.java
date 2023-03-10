package sk.stuba.fei.uim.oop.table;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Table {
    private ArrayList<Card> deck;
    private ArrayList<Card> discardDeck;

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
        this.discardDeck = new ArrayList<Card>();
    }

    public ArrayList<Card> drawCards(int numberOfCards) {
        ArrayList<Card> outputCards = new ArrayList<Card>();
        for (int i = 0; i < numberOfCards; i++){
            if(deck.isEmpty()){
                reffillDeck();
            } else {
                outputCards.add(deck.get(0));
                deck.remove(0);
            }
        }
        return outputCards;
    }

    public void discardCard(Card card) {
        this.discardDeck.add(card);
    }

    public void discardCards(ArrayList<Card> cards) {
        this.discardDeck.addAll(cards);
    }

    public void reffillDeck(){
        if (!(discardDeck.isEmpty())) {
            Collections.shuffle(this.discardDeck);
            deck.addAll(discardDeck);
            discardDeck.clear();
        } else {
            System.out.println("Vsetky balicky su prazdne.");
        }
    }
}
