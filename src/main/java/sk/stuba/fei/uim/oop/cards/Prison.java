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
        int targetIndex = this.choosePlayer(player);
        if(targetIndex == -1) {
            return false;
        }
        Player targetPlayer = game.getPlayerByIndex(targetIndex);
        if(targetPlayer.checkCardTable(Prison.class, false)){
            System.out.println("You can not have two blue cards of the same type on the table at once!");
            return false;
        }
        targetPlayer.setCardsOnTable(player.removeCardOnHand(this));
        return false;
    }

    @Override
    public boolean receivePlay(Player player) {
        boolean returnVal = true;
        if ((randomGenerator.nextInt(4) + 1) == 1) {
            returnVal = false;
        }
        table.discardCard(player.removeCardOnTable(this));
        return returnVal;
    }
}
