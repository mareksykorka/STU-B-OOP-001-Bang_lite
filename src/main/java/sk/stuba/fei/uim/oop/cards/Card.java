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

    protected Player chooseTarget(ArrayList<Player> playablePLayers, String options, String question) {
        System.out.println("═════════════════ CHOOSE TARGET ═════════════════");
        if(playablePLayers.size() == 1){
            System.out.println("You have only one enemy player - automatically choosing player " + playablePLayers.get(0).getName());
            return playablePLayers.get(0);
        }
        System.out.print(options);
        return playablePLayers.get(this.pickIndex(question, playablePLayers.size()));
    }

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
}
