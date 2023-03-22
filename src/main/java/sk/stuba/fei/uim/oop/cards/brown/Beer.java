package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;

public class Beer extends BrownCard {
    private static final String CARD_NAME = "Beer";

    public Beer() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        deck.discardCard(activePlayer.removeCardsOnHand(this));
        activePlayer.setStatusMessage(TxtDef.CLI_INFO + activePlayer.getName() + " -> Life gained.");
        activePlayer.addLives(1);
        return;
    }
    @Override
    public boolean receivePlay(Player targetPlayer, ArrayList<Player> alivePlayers, Deck deck) {
        return true;
    }
}
