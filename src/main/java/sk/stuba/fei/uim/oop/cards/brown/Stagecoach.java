package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;

public class Stagecoach extends BrownCard {
    private static final String CARD_NAME = "Stagecoach";

    public Stagecoach() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        deck.discardCard(activePlayer.removeCardsOnHand(this));
        activePlayer.setStatusMessage(TxtDef.CLI_INFO + activePlayer.getName() + " -> Two cards drawn.");
        activePlayer.setCardsOnHand(deck.drawCards(2));
        return;
    }
    @Override
    public boolean receivePlay(Player targetPlayer, ArrayList<Player> alivePlayers, Deck deck) {
        return true;
    }
}
