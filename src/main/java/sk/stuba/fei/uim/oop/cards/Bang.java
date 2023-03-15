package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";

    public Bang() {
        super(CARD_NAME, Colour.BROWN);
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        deck.discardCard(activePlayer.removeCardOnHand(this));
        Player targetPlayer = chooseTarget(enemyPlayers);
        if(targetPlayer.checkCardTable(Barrel.class, deck)){
            return true;
        }
        if(targetPlayer.checkCardHand(Missed.class, deck)){
            return true;
        }
        targetPlayer.removeLives(1);
        return true;
    }

    private Player chooseTarget(ArrayList<Player> enemyPlayers) {
        if(enemyPlayers.size() == 1){
            return enemyPlayers.get(0);
        }

        System.out.println("═════════════════ CHOOSE TARGET ═════════════════");
        for(int i = 0; i < enemyPlayers.size(); i++) {
            System.out.println((i + 1) + ". " + enemyPlayers.get(i).getName() + " " +
                    enemyPlayers.get(i).aliveStatus());
        }
        return enemyPlayers.get(this.pickIndex("Select player you want to use the BANG on", enemyPlayers.size()));
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        deck.discardCard(targetPlayer.removeCardOnHand(this));
        System.out.println("INDIANS evaded by BANG");
        return true;
    }
}
