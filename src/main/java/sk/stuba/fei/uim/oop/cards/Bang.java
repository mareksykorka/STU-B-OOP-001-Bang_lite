package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";

    public Bang() {
        super(CARD_NAME, Colour.BROWN);
    }

    @Override
    public boolean play(Player player, Table table, Game game) {
        int input = ZKlavesnice.readInt("Who is the target ?");
        input -= 1;
        if((input >= 0) && (input < game.getNumberOfAllPlayers())){
            if(input != game.getIndexOfActivePlayer()) {
                Player target = game.getPlayerByIndex(input);
                if(target.isAlive()){
                    target.receiveBang(table);
                    return true;
                }
                return false;
            }
            System.out.println("You can not shoot yourself!");
            return false;
        }
        return false;
    }
}
