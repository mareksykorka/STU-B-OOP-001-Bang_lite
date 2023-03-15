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
    /*
    public String getPlayable(){

    }*/


   /* protected int choosePlayer(Player player){
        int input = ZKlavesnice.readInt("Who is the target ?");
        input -= 1;
        if((input >= 0) && (input < this.game.getNumberOfAllPlayers())){
            if(input != game.getIndexOfActivePlayer()) {
                Player target = game.getPlayerByIndex(input);
                if(target.isAlive()){
                    return input;
                }
                System.out.println("You can not target dead players!");
                return -1;
            }
            System.out.println("You can not target yourself!");
            return -1;
        }
        System.out.println("There is no such player.");
        return -1;
    }*/

    public abstract boolean play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck);

    public abstract boolean receivePlay(Player targetPlayer, Deck deck);
}
