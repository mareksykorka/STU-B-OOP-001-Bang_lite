package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";

    public Prison(Table table, Game game) {
        super(CARD_NAME, Colour.BLUE, table, game);
    }

    @Override
    public boolean play(Player player) {
        return false;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }
}
