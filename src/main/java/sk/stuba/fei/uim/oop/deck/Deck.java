package sk.stuba.fei.uim.oop.deck;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.cards.blue.*;
import sk.stuba.fei.uim.oop.cards.brown.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> discardPile;
    private String gameStatusMessage;

    public Deck(ArrayList<Player> players) {
        Random randomGenerator = new Random();
        ArrayList<Card> cards = new ArrayList<>();
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
        cards.add(new Barrel(randomGenerator));
        cards.add(new Barrel(randomGenerator));
        cards.add(new Dynamite(randomGenerator, players));
        cards.add(new Prison(randomGenerator));
        cards.add(new Prison(randomGenerator));
        cards.add(new Prison(randomGenerator));

        Collections.shuffle(cards);

        this.deck = cards;
        this.discardPile = new ArrayList<>();
        this.gameStatusMessage = "";
    }

    public String getStatusMessage() {
        String outString = this.gameStatusMessage;
        this.gameStatusMessage = "";
        return outString;
    }

    public void setStatusMessage(String message) {
        this.gameStatusMessage += message + "\n";
    }

    public ArrayList<Card> drawCards(int numberOfCards) {
        ArrayList<Card> outputCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (this.deck.isEmpty()) {
                if (this.reffillDeck()) {
                    outputCards.add(this.deck.remove(0));
                } else {
                    break;
                }
            } else {
                outputCards.add(this.deck.remove(0));
            }
        }
        return outputCards;
    }

    private boolean reffillDeck() {
        if (!(this.discardPile.isEmpty())) {
            this.setStatusMessage(TxtDef.ANSI_BOLD + TxtDef.CLI_INFO + "Refilling deck, from the discard pile." + TxtDef.ANSI_RESET);
            Collections.shuffle(this.discardPile);
            this.deck.addAll(this.discardPile);
            this.discardPile.clear();
            return true;
        }
        this.setStatusMessage(TxtDef.ANSI_BRIGHT_RED + TxtDef.ANSI_BOLD + TxtDef.CLI_WARNING +
                "There are no more cards in deck nor discard pile." + TxtDef.ANSI_RESET);
        return false;
    }

    public void discardCard(Card card) {
        this.discardPile.add(card);
    }

    public void discardCard(ArrayList<Card> cards) {
        this.discardPile.addAll(cards);
    }

    public void playerDeath(Player player) {
        this.discardCard(player.removeCardsOnTable());
        this.discardCard(player.removeCardsOnHand());
    }
}
