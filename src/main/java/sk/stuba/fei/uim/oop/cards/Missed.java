package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";

    public Missed(Table table, Game game) {
        super(CARD_NAME, Colour.BROWN);
    }

    @Override
    public boolean play(Player player, Table table, Game game) {
        return true;
    }
}
