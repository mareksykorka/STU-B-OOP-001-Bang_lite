package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";

    public Missed(Table table, Game game) {
        super(CARD_NAME, Colour.BROWN, table, game);
    }

    @Override
    public boolean play(Player player) {
        char input = ZKlavesnice.readChar("You can not play " + this.getName() + ". Do you want to throw it away into discard pile? (Y/N)");
        if(input == 'Y' || input == 'y'){
            return true;
        }
        return false;
    }

    @Override
    public boolean receivePlay(Player player) {
        table.discardCard(player.removeCardOnHand(this));
        System.out.println("BANG evaded by MISSED");
        return true;
    }
}
