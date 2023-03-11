package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

public class Barrel extends Card {
    private static final String CARD_NAME = "Barrel";

    public Barrel(Table table, Game game) {
        super(CARD_NAME, Colour.BLUE, table, game);
    }

    @Override
    public boolean play(Player player) {
        for (Card card:player.getCardsOnTable()) {
            if(card instanceof Barrel)
            {
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
        if ((randomGenerator.nextInt(4) + 1) == 1) {
            return true;
        }
        return false;
    }
}
