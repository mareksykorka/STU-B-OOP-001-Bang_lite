package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.utility.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.deck.Deck;

import java.util.ArrayList;

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
            return TxtDef.ANSI_DARK_YELLOW + name + TxtDef.ANSI_RESET;
        }
        if(this.cardColour == Colour.BLUE){
            return TxtDef.ANSI_DARK_BLUE + name + TxtDef.ANSI_RESET;
        }
        return name;
    }

    public abstract boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck);

    public abstract boolean receivePlay(Player targetPlayer, Deck deck);

    protected int pickIndex(String message, int max){
        return this.pickIndex(message, 1, max);
    }

    protected int pickIndex(String message, int min, int max){
        if(min == max) {
            return max-1;
        }

        int input;
        do {
            input = ZKlavesnice.readInt(message + "("+min+"-"+max+"):");
        } while (input < min || input > max);

        return input-1;
    }

    protected void printGameStatus(String status){
        System.out.println(TxtDef.CLI_CLS);
        System.out.println("══════════════════ GAME STATUS ══════════════════");
        System.out.println(status);
    }

    protected void printGameStatus(){
        System.out.println(TxtDef.CLI_CLS);
        System.out.println("══════════════════ GAME STATUS ══════════════════");
    }
}
