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
        player.removeCardOnHand(this);
        return false;
    }

    @Override
    public boolean receivePlay(Player player) {
        if ((randomGenerator.nextInt(8) + 1) == 1) {
            System.out.println("DYNAMITE exploded.");
            if(!player.removeLives(3)){
                game.playerDeath(player);
            }
            table.discardCard(player.removeCardOnTable(this));
            return true;
        }
        System.out.println("DYNAMIT moving.");
        Player prevPlayer = game.getPlayerByIndex(game.prevPlayer());
        prevPlayer.setCardsOnTable(player.removeCardOnTable(this));
        return false;
    }
}
