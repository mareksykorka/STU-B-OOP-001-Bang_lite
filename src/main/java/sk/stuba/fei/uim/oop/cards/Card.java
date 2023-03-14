package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.Random;

public abstract class Card {
    private String name;
    protected enum Colour {
        BLUE,
        BROWN
    }
    protected Colour cardColour;

    Game game;
    Table table;
    Random randomGenerator;

    public Card(String name, Colour colour, Table table, Game game) {
        this.name = name;
        this.cardColour = colour;
        this.table = table;
        this.game = game;
        this.randomGenerator = new Random();
    }

    public String getName() {
        if(this.cardColour == Colour.BROWN){
            return "\u001B[33m" + name + "\u001B[0m";
        }
        if(this.cardColour == Colour.BLUE){
            return "\u001B[34m" + name + "\u001B[0m";
        }
        return name;
    }

    //TODO: Implement CLI if playable
    /*
    public String getPlayable(){

    }*/


    protected int choosePlayer(Player player){
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
    }

    public abstract boolean play(Player player);

    public abstract boolean receivePlay(Player player);
}
