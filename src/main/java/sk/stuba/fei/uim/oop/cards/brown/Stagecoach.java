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
        ArrayList<Card> drawnCards = deck.drawCards(2);
        deck.setStatusMessage(TxtDef.CLI_INFO + activePlayer.getName() + " -> " + drawnCards.size() + " cards drawn.");
        deck.discardCard(activePlayer.removeCardsOnHand(this));
        activePlayer.setCardsOnHand(drawnCards);
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        return true;
    }
}
