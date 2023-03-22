package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;

public class Indians extends BrownCard {
    private static final String CARD_NAME = "Indians";

    public Indians() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        deck.discardCard(activePlayer.removeCardsOnHand(this));
        for (Player targetPlayer : enemyPlayers) {
            if (!targetPlayer.checkCardHand(Bang.class, deck)) {
                deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> Life lost.");
                targetPlayer.removeLives(1, deck);
                if (!targetPlayer.isAlive()) {
                    deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> Died, Killed by " +
                            activePlayer.getName() + "'s " + this.getName() + ".");
                }
            }
        }
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        return true;
    }
}
