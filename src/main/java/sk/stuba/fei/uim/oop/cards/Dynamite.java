package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite";

    public Dynamite() {
        super(CARD_NAME, Colour.BLUE);
    }

    @Override
    public boolean play(Player player, Table table, Game game) {
        return false;
    }
}
