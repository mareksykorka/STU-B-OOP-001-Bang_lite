package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.utility.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.deck.Deck;

import java.util.ArrayList;
import java.util.Random;

public abstract class Card {
    private String name;
    protected enum Colour {
        BLUE,
        BROWN
    }
    protected Colour cardColour;

    public Card(String name, Colour colour) {
        this.name = name;
        this.cardColour = colour;
    }

    public String getName() {
        if(this.cardColour == Colour.BROWN){
            return TxtModif.ANSI_DARK_YELLOW + name + TxtModif.ANSI_RESET;
        }
        if(this.cardColour == Colour.BLUE){
            return TxtModif.ANSI_DARK_BLUE + name + TxtModif.ANSI_RESET;
        }
        return name;
    }

    //TODO: Implement CLI if playable

    public abstract boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck);

    public abstract boolean receivePlay(Player targetPlayer, Deck deck);

    public int pickIndex(String message, int max){
        return this.pickIndex(message, 1, max);
    }

    public int pickIndex(String message, int min, int max){
        if(min == max) {
            return max-1;
        }

        int input;
        do {
            input = ZKlavesnice.readInt(message + "("+min+"-"+max+"):");
        } while (input < min || input > max);

        return input-1;
    }
}
