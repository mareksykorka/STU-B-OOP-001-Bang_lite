package sk.stuba.fei.uim.oop.table;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.game.Game;

import java.util.ArrayList;
import java.util.Collections;

public class Table {
    private ArrayList<Card> deck;
    private ArrayList<Card> discardPile;

    public Table(Game game) {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < 30; i++) {
            cards.add(new Bang(this, game));
        }
        for (int i = 0; i < 15; i++) {
            cards.add(new Missed(this, game));
        }
        for (int i = 0; i < 8; i++) {
            cards.add(new Beer(this, game));
        }
        for (int i = 0; i < 6; i++) {
            cards.add(new CatBalou(this, game));
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new Stagecoach(this, game));
        }
        cards.add(new Indians(this, game));
        cards.add(new Indians(this, game));
        cards.add(new Barrel(this, game));
        cards.add(new Barrel(this, game));
        cards.add(new Dynamite(this, game));
        cards.add(new Prison(this, game));
        cards.add(new Prison(this, game));
        cards.add(new Prison(this, game));

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
        if(this.deck.isEmpty()) {
            return (this.reffillDeck() ? this.drawCards() : null);
        }
        return this.deck.remove(0);
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
            System.out.println("Reffiling deck from the discard pile.");
            deck.addAll(discardPile);
            discardPile.clear();
            return true;
        }

        System.out.println("There are no more cards left on the table, neither in the deck nor in the discard pile.");
        return false;
    }
}
