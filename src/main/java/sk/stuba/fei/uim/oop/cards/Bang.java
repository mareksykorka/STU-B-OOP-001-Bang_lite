package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";

    public Bang() {
        super(CARD_NAME, Colour.BROWN);
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        deck.discardCard(activePlayer.removeCardsOnHand(this));

        String options = "";
        for(int i = 0; i < enemyPlayers.size(); i++) {
            options += (i + 1) + ". " + enemyPlayers.get(i).getName() + " " + enemyPlayers.get(i).isAliveToString() + "\n";
        }
        Player targetPlayer = this.chooseTarget(enemyPlayers, options, "Who do you want to shoot using " + this.getName() + " ");

        if(targetPlayer.checkCardTable(Barrel.class, deck)){
            return true;
        }
        if(targetPlayer.checkCardHand(Missed.class, deck)){
            return true;
        }
        targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> Life lost.");
        targetPlayer.removeLives(1);
        if(!targetPlayer.isAlive()){
            targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> Died, Killed by " +
                    activePlayer.getName() + "'s "+ this.getName() + ".");
        }
        return true;
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        deck.discardCard(targetPlayer.removeCardsOnHand(this));
        targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + "-> INDIANS evaded by " + this.getName());
        return true;
    }
}
