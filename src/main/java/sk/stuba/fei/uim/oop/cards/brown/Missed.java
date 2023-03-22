package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;

public class Missed extends BrownCard {
    private static final String CARD_NAME = "Missed";

    public Missed() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        activePlayer.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> " + this.getName() + " is non-playable Card!");
        return;
    }
    @Override
    public boolean receivePlay(Player targetPlayer, ArrayList<Player> alivePlayers, Deck deck) {
        deck.discardCard(targetPlayer.removeCardsOnHand(this));
        targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> BANG evaded by " + this.getName());
        return true;
    }
}
