package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

public abstract class Card {
    private String name;
    enum Colour {
        BLUE,
        BROWN
    }
    private Colour cardColour;

    public Card(String name, Colour colour) {
        this.name = name;
        this.cardColour = colour;
    }

    public String getName() {
        return name;
    }

    //TODO: efekty presun do kariet
    public abstract boolean play(Player player, Table table, Game game);
}
