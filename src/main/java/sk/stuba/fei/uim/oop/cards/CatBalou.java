package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CatBalou";

    public CatBalou(Table table, Game game) {
        super(CARD_NAME, Colour.BROWN, table, game);
    }

    @Override
    public boolean play(Player player, Table table, Game game) {
        return false;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }
}
