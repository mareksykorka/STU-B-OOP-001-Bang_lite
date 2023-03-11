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
        boolean returnVal = false;
        for (int i = 0; i < game.getNumberOfAllPlayers(); i++) {
            if(game.getPlayerByIndex(i).isAlive()){
                Player targetPlayer = game.getPlayerByIndex(i);
                if(!(player.equals(targetPlayer))){
                    for (Card card:targetPlayer.getCardsOnHand()) {
                        if(card instanceof Bang) {
                            returnVal = card.receivePlay(targetPlayer);
                        }
                    }
                }
            }
        }
        return returnVal;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }
}
