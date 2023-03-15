package sk.stuba.fei.uim.oop.deck;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> discardPile;

    public Deck() {
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
                System.out.println("There are no more cards left on the table,\nneither in the deck nor in the discard pile.");
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
    private boolean reffillDeck(){
        if (!(this.discardPile.isEmpty())) {
            Collections.shuffle(this.discardPile);
            System.out.println("Refiling deck from the discard pile.");
            this.deck.addAll(this.discardPile);
            this.discardPile.clear();
            return true;
        }
        return false;
    }

    public void discardCard(Card card) {
        this.discardPile.add(card);
    }
    public void discardCard(ArrayList<Card> cards) {
        this.discardPile.addAll(cards);
    }

    public int getNumberOfCardsInDeck(){
        return this.deck.size();
    }
    public int getNumberOfCardsInDiscardPile(){
        return this.discardPile.size();
    }

    public void playerDeath(Player player){
        this.discardCard(player.removeCardOnTable());
        this.discardCard(player.removeCardOnHand());
    }
}
