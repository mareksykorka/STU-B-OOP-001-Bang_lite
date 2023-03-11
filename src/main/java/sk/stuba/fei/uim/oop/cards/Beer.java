package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";

    public Beer(Table table, Game game) {
        super(CARD_NAME, Colour.BROWN, table, game);
    }

    @Override
    public boolean play(Player player) {
        player.addLives(1);
        return true;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }
}
