package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";

    public Missed() {
        super(CARD_NAME, Colour.BROWN);
    }

    @Override
    public boolean play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        this.printGameStatus(TxtDef.CLI_WARNING + activePlayer.getName() + "-> " + this.getName() + " is non-playable Card!");
        return true;
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        deck.discardCard(targetPlayer.removeCardOnHand(this));
        System.out.println(TxtDef.CLI_INFO + targetPlayer.getName() + "-> BANG evaded by " + this.getName());
        return true;
    }
}
