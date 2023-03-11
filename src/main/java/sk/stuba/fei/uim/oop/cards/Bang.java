package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";

    public Bang(Table table, Game game) {
        super(CARD_NAME, Colour.BROWN, table, game);
    }

    @Override
    public boolean play(Player player) {
        int targetIndex = this.choosePlayer(player);
        if(targetIndex == -1) {
            return false;
        }
        Player targetPlayer = game.getPlayerByIndex(targetIndex);
        for (Card card:targetPlayer.getCardsOnTable()) {
            if(card instanceof Barrel)
            {
                return card.receivePlay(targetPlayer);
            }
        }
        for (Card card:targetPlayer.getCardsOnHand()) {
            if(card instanceof Missed)
            {
                return card.receivePlay(targetPlayer);
            }
        }
        targetPlayer.removeLives(1);
        return true;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }
}
