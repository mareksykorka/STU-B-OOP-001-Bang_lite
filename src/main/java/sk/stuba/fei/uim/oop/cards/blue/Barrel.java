package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.TxtDef;

import java.util.ArrayList;
import java.util.Random;

public class Barrel extends BlueCard {
    private static final String CARD_NAME = "Barrel";

    public Barrel(Random randomGenerator) {
        super(CARD_NAME, randomGenerator);
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> alivePlayers, Deck deck) {
        if (this.canBePlacedOnTable(activePlayer.getCardsOnTable(), Barrel.class)) {
            deck.setStatusMessage(TxtDef.CLI_INFO + activePlayer.getName() + " -> " + this.getName() + " placed on table.");
            activePlayer.setCardsOnTable(activePlayer.removeCardsOnHand(this));
        } else {
            deck.setStatusMessage(TxtDef.CLI_WARNING + activePlayer.getName() + " -> You can not have two " + this.getName() + "'s on the table at once!");
        }
    }

    @Override
    public boolean receivePlay(Player targetPlayer, Deck deck) {
        if (this.checkChance((double) 1 / 4)) {
            deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> BANG evaded by " + this.getName());
            return true;
        }
        deck.setStatusMessage(TxtDef.CLI_INFO + targetPlayer.getName() + " -> " + this.getName() + " not effective.");
        return false;
    }
}
