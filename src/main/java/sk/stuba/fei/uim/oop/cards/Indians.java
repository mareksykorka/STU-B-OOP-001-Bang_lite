package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians(Table table, Game game) {
        super(CARD_NAME, Colour.BROWN, table, game);
    }

    @Override
    public boolean play(Player player) {
        /*for (int i = 0; i < game.getNumberOfAllPlayers(); i++) {
            if(game.getPlayerByIndex(i).isAlive()){
                Player target = game.getPlayerByIndex(i);
                if(!(player.equals(target))){
                    target.receiveIndians(table);
                }
            }
        }*/
        return true;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }
}
