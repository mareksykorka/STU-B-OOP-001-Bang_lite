package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";

    public Missed() {
        super(CARD_NAME);
    }

    @Override
    public String getName() {
        return TxtDef.ANSI_DARK_YELLOW + super.getName() + TxtDef.ANSI_RESET;
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        activePlayer.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> " + this.getName() + " is non-playable Card!");
        return true;
    }
    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        deck.discardCard(targetPlayer.removeCardsOnHand(this));
        targetPlayer.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> BANG evaded by " + this.getName());
        return true;
    }
}
