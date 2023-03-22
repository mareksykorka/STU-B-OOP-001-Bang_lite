package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "Stagecoach";

    public Stagecoach() {
        super(CARD_NAME);
    }

    @Override
    public String getName() {
        return TxtDef.ANSI_DARK_YELLOW + super.getName() + TxtDef.ANSI_RESET;
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> enemyPlayers, Deck deck) {
        deck.discardCard(activePlayer.removeCardsOnHand(this));
        activePlayer.setStatusMessage(TxtDef.CLI_INFO + activePlayer.getName() + " -> Two cards drawn.");
        activePlayer.setCardsOnHand(deck.drawCards(2));
        return true;
    }
    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        return true;
    }
}
