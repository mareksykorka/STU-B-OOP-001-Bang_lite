package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite";

    public Dynamite(Table table, Game game) {
        super(CARD_NAME, Colour.BLUE, table, game);
    }

    @Override
    public boolean play(Player player) {
        for (Card card:player.getCardsOnTable()) {
            if(card instanceof Dynamite) {
                System.out.println("You can not have two blue cards of the same type on the table at once!");
                return false;
            }
        }
        player.setCardsOnTable(this);
        return true;
    }

    @Override
    public boolean receivePlay(Player player) {
        if ((randomGenerator.nextInt(8) + 1) == 1) {
            player.removeLives(3);
            table.discardCard(player.removeCardOnTable(this));
            return true;
        }
        Player prevPlayer = game.getPlayerByIndex(game.prevPlayer());
        prevPlayer.setCardsOnTable(player.removeCardOnTable(this));
        return false;
    }
}
