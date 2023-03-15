package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians(Table table, Game game) {
        super(CARD_NAME, Colour.BROWN, table, game);
    }

    @Override
    public boolean play(Player player) {
        for (int i = 0; i < game.getNumberOfAllPlayers(); i++) {
            Player targetPlayer = game.getPlayerByIndex(i);
            if(targetPlayer.isAlive() && !(targetPlayer.equals(player))){
                ArrayList<Card> iteratingList = new ArrayList<Card>();
                iteratingList.addAll(targetPlayer.getCardsOnHand());
                for (Card card:iteratingList) {
                    if(card instanceof Bang) {
                        card.receivePlay(targetPlayer);
                    }
                }
                if(!targetPlayer.removeLives(1)){
                    game.playerDeath(targetPlayer);
                }
            }
        }
        return true;
    }

    @Override
    public boolean receivePlay(Player player) {
        return false;
    }
}
